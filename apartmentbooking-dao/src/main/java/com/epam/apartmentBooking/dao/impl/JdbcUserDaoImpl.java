package com.epam.apartmentBooking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.epam.apartmentBooking.dao.IUserDao;
import com.epam.apartmentBooking.model.User;

@Repository("jdbcUserDao")
public class JdbcUserDaoImpl implements IUserDao {

	private static final String SQL_QUERY_FIND_ALL_USER = "select USER_ID,USER_NAME,USER_SURNAME,USER_EMAIL,USER_PASSWORD from USERS";
	private static final String SQL_QUERY_INSERT_USER = "insert into USERS (USER_NAME,USER_SURNAME,USER_EMAIL,USER_PASSWORD) values (:USER_NAME,:USER_SURNAME,:USER_EMAIL,:USER_PASSWORD)";
	private static final String SQL_QUERY_DELETE_USER = "delete from USERS where USER_ID = :USER_ID";
	private static final String SQL_QUERY_UPDATE_USER = "update USERS set USER_NAME = :USER_NAME,USER_SURNAME = :USER_SURNAME,USER_EMAIL = :USER_EMAIL,USER_PASSWORD = :USER_PASSWORD where USER_ID = :USER_ID";
	private static final String SQL_QUERY_FIND_USER_BY_ID = "select * from USERS where USER_ID = :USER_ID";
	private static final String SQL_QUERY_CHECK_USER = "select COUNT(*) from USERS where USER_EMAIL = :USER_EMAIL and USER_PASSWORD = :USER_PASSWORD";
	private static final String SQL_QUERY_CHECK_EMAIL = "select COUNT(*) from USERS where USER_EMAIL = :USER_EMAIL";

	private NamedParameterJdbcOperations namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

	}

	@Override
	public List<User> findAll() {
		return namedParameterJdbcTemplate.query(SQL_QUERY_FIND_ALL_USER, new UserRowMapper());
	}

	@Override
	public long create(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("USER_NAME", user.getName());
		params.addValue("USER_SURNAME", user.getSurname());
		params.addValue("USER_EMAIL", user.getEmail());
		params.addValue("USER_PASSWORD", user.getPassword());
		return namedParameterJdbcTemplate.update(SQL_QUERY_INSERT_USER, params);
	}

	@Override
	public void delete(Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("USER_ID", id);
		namedParameterJdbcTemplate.update(SQL_QUERY_DELETE_USER, params);
	}

	@Override
	public long update(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("USER_ID", user.getId());
		params.addValue("USER_NAME", user.getName());
		params.addValue("USER_SURNAME", user.getSurname());
		params.addValue("USER_EMAIL", user.getEmail());
		params.addValue("USER_PASSWORD", user.getPassword());
		return namedParameterJdbcTemplate.update(SQL_QUERY_UPDATE_USER, params);
	}

	@Override
	public boolean checkUser(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("USER_EMAIL", user.getEmail());
		params.addValue("USER_PASSWORD", user.getPassword());
		return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_CHECK_USER, params, Boolean.class);
	}

	@Override
	public boolean checkEmail(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("USER_EMAIL", user.getEmail());
		return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_CHECK_EMAIL, params, Boolean.class);
	}

	@Override
	public User findById(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("USER_ID", id);
		return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_FIND_USER_BY_ID, params, new UserRowMapper());
	}

	private static final class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("USER_ID"));
			user.setName(rs.getString("USER_NAME"));
			user.setSurname(rs.getString("USER_SURNAME"));
			user.setEmail(rs.getString("USER_EMAIL"));
			user.setPassword(rs.getString("USER_PASSWORD"));
			return user;

		}

	}
}

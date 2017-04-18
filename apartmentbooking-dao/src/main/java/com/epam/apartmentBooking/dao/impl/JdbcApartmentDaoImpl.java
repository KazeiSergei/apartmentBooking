package com.epam.apartmentBooking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.epam.apartmentBooking.dao.IApartmentDao;
import com.epam.apartmentBooking.model.Apartment;

@Repository("apartmentDao")
public class JdbcApartmentDaoImpl implements IApartmentDao {

	private static final String SQL_QUERY_FIND_ALL_APARTMENT = "select * from APARTMENTS";
	private static final String SQL_QUERY_CREATE_APARTMENT = "insert into APARTMENTS (AP_NAME,AP_DESCRIPTION,AP_GUEST_COUNT,AP_COST_PER_DAY,AP_APARTMENT_TYPE_ID,AP_CITY_ID) values (:AP_NAME,:AP_DESCRIPTION,:AP_GUEST_COUNT,:AP_COST_PER_DAY,:AP_APARTMENT_TYPE_ID,:AP_CITY_ID)";
	private static final String SQL_QUERY_DELETE_APARTMENT = "delete from APARTMENTS where AP_ID = :AP_ID";
	private static final String SQL_QUERY_UPDATE_APARTMENT = "update APARTMENTS set AP_NAME = :AP_NAME,AP_DESCRIPTION = :AP_DESCRIPTION,AP_GUEST_COUNT = :AP_GUEST_COUNT,AP_COST_PER_DAY = :AP_COST_PER_DAY,AP_APARTMENT_TYPE_ID = :AP_APARTMENT_TYPE_ID,AP_CITY_ID = :AP_CITY_ID  where AP_ID = :AP_ID";
	private static final String SQL_QUERY_FIND_APARTMENT_BY_ID = "select * from APARTMENTS where AP_ID = :AP_ID";

	private NamedParameterJdbcOperations namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Apartment> findAll() {
		return namedParameterJdbcTemplate.query(SQL_QUERY_FIND_ALL_APARTMENT, new ApartmentRowMapper());
	}

	@Override
	public long create(Apartment apartment) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AP_NAME", apartment.getName());
		params.addValue("AP_DESCRIPTION", apartment.getDescription());
		params.addValue("AP_GUEST_COUNT", apartment.getGuestCount());
		params.addValue("AP_COST_PER_DAY", apartment.getCostPerDay());
		params.addValue("AP_APARTMENT_TYPE_ID", apartment.getApartmentTypeId());
		params.addValue("AP_CITY_ID", apartment.getCityId());
		return namedParameterJdbcTemplate.update(SQL_QUERY_CREATE_APARTMENT, params);
	}

	@Override
	public void delete(Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AP_ID", id);
		namedParameterJdbcTemplate.update(SQL_QUERY_DELETE_APARTMENT, params);
	}

	@Override
	public long update(Apartment apartment) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AP_ID", apartment.getId());
		params.addValue("AP_NAME", apartment.getName());
		params.addValue("AP_DESCRIPTION", apartment.getDescription());
		params.addValue("AP_GUEST_COUNT", apartment.getGuestCount());
		params.addValue("AP_COST_PER_DAY", apartment.getCostPerDay());
		params.addValue("AP_APARTMENT_TYPE_ID", apartment.getApartmentTypeId());
		params.addValue("AP_CITY_ID", apartment.getCityId());
		return namedParameterJdbcTemplate.update(SQL_QUERY_UPDATE_APARTMENT, params);
	}

	@Override
	public Apartment findById(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("AP_ID", id);
		return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_FIND_APARTMENT_BY_ID, params, new ApartmentRowMapper());
	}

	private static final class ApartmentRowMapper implements RowMapper<Apartment> {
		@Override
		public Apartment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Apartment apartment = new Apartment();
			apartment.setId(rs.getLong("AP_ID"));
			apartment.setName(rs.getString("AP_NAME"));
			apartment.setDescription(rs.getString("AP_DESCRIPTION"));
			apartment.setGuestCount(rs.getInt("AP_GUEST_COUNT"));
			apartment.setCostPerDay(rs.getInt("AP_COST_PER_DAY"));
			apartment.setApartmentTypeId(rs.getLong("AP_APARTMENT_TYPE_ID"));
			apartment.setCityId(rs.getLong("AP_CITY_ID"));
			return apartment;
		}
	}

}

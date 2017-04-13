package com.epam.apartmentBooking.dao.impl;

import com.epam.apartmentBooking.dao.ICityDao;
import com.epam.apartmentBooking.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("jdbcCityDao")
public class JdbcCityDaoImpl implements ICityDao {

    private static final String SQL_QUERY_FIND_ALL_CITY = "select * from CITIES";

    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    @Override
    public List<City> findAll() {
        return namedParameterJdbcTemplate.query(SQL_QUERY_FIND_ALL_CITY, new CityRowMapper());
    }

    @Override
    public long create(City entity) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    @Override
    public long update(City entity) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    @Override
    public City findById(long id) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    private static final class CityRowMapper implements RowMapper<City> {

        @Override
        public City mapRow(ResultSet rs, int rowNum) throws SQLException {
            City city = new City();
            city.setId(rs.getLong("CITY_ID"));
            city.setName(rs.getString("CITY_NAME"));
            return city;
        }

    }
}

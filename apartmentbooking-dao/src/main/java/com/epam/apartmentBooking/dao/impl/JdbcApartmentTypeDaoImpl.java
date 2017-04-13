package com.epam.apartmentBooking.dao.impl;

import com.epam.apartmentBooking.dao.IApartmentTypeDao;
import com.epam.apartmentBooking.model.ApartmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("jdbcApartmentTypeDao")
public class JdbcApartmentTypeDaoImpl implements IApartmentTypeDao {

    private static final String SQL_QUERY_FIND_ALL_APARTMENT_TYPE = "select * from APARTMENT_TYPES";

    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    @Override
    public List<ApartmentType> findAll() {
        return namedParameterJdbcTemplate.query(SQL_QUERY_FIND_ALL_APARTMENT_TYPE, new ApartmentTypeRowMapper());
    }

    @Override
    public long create(ApartmentType entity) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    @Override
    public long update(ApartmentType entity) {
        throw new UnsupportedOperationException("this operation doesn't support");
    }

    private static final class ApartmentTypeRowMapper implements RowMapper<ApartmentType> {

        @Override
        public ApartmentType mapRow(ResultSet rs, int rowNum) throws SQLException {
            ApartmentType apartmentType = new ApartmentType();
            apartmentType.setId(rs.getLong("APARTMENT_TYPE_ID"));
            apartmentType.setApartmentType(rs.getString("APARTMENT_TYPE_TITLE"));
            return apartmentType;
        }

    }

}

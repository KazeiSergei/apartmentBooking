package com.epam.apartmentBooking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.epam.apartmentBooking.dao.IApartmentFilterDao;
import com.epam.apartmentBooking.model.ApartmentFilter;
import com.epam.apartmentBooking.model.ApartmentType;
import com.epam.apartmentBooking.model.City;

@Repository("apartmentFilterDao")
public class JdbcApartmentFilterDaoImpl implements IApartmentFilterDao {

	private static final String SQL_QUERY_FIND_APARTMENT_BY_CITY = "select APARTMENTS.AP_ID,APARTMENTS.AP_DESCRIPTION,APARTMENTS.AP_GUEST_COUNT,APARTMENTS.AP_COST_PER_DAY," + "APARTMENT_TYPES.APARTMENT_TYPE_TITLE,CITIES.CITY_NAME from APARTMENTS "
			+ "join APARTMENT_TYPES on APARTMENTS.AP_APARTMENT_TYPE_ID = APARTMENT_TYPES.APARTMENT_TYPE_ID " + "join CITIES on APARTMENTS.AP_CITY_ID = CITIES.CITY_ID where CITIES.CITY_NAME like :CITY_NAME";
	private static final String SQL_QUERY_FILTER_APARTMENT = "select * from APARTMENTS join APARTMENT_TYPES on APARTMENTS.AP_APARTMENT_TYPE_ID = APARTMENT_TYPES.APARTMENT_TYPE_ID inner join CITIES on APARTMENTS.AP_CITY_ID = CITIES.CITY_ID where APARTMENTS.AP_GUEST_COUNT >= ':AP_GUEST_COUNT' and CITIES.CITY_NAME like CONCAT('%', CONCAT(':CITY_NAME', '%')) and APARTMENTS.AP_ID not in ( select applications.appl_apartment_id from applications where applications.APPL_CHECK_OUT_DATE > to_date(':CHECK_IN_DATE','YYYY-MM-DD') and applications.APPL_CHECK_IN_DATE < to_date(':CHECK_OUT_DATE','YYYY-MM-DD'))";
	private static final String SQL_QUERY_FILTER_APARTMENT1 = "select * from APARTMENTS join APARTMENT_TYPES on APARTMENTS.AP_APARTMENT_TYPE_ID = APARTMENT_TYPES.APARTMENT_TYPE_ID inner join CITIES on APARTMENTS.AP_CITY_ID = CITIES.CITY_ID where APARTMENTS.AP_GUEST_COUNT >= :AP_GUEST_COUNT and CITIES.CITY_NAME like CONCAT('%', CONCAT(:CITY_NAME, '%')) and APARTMENTS.AP_ID not in ( select applications.appl_apartment_id from applications where applications.APPL_CHECK_OUT_DATE > to_date(:CHECK_IN_DATE,'YYYY-MM-DD') and applications.APPL_CHECK_IN_DATE < to_date(:CHECK_OUT_DATE,'YYYY-MM-DD'))";

	private NamedParameterJdbcOperations namedParameterJdbcTemplate;

	private static final String FORMAT_DATE = "yyyy-MM-dd";

	@Autowired
	public void setNamedParameterTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

	}

	@Override
	public List<ApartmentFilter> findApartmentByCity(City city) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("CITY_NAME", city.getName());
		return namedParameterJdbcTemplate.query(SQL_QUERY_FIND_APARTMENT_BY_CITY, params, new ApartmentFilterRowMapper());
	}

	@Override
	public List<ApartmentFilter> findAvailableApartment(City city, int guest, Date checkIn, Date checkOut) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("CITY_NAME", city.getName());
		params.addValue("AP_GUEST_COUNT", guest);
		params.addValue("CHECK_IN_DATE", dateFormat.format(checkIn));
		System.out.println(checkIn);
		params.addValue("CHECK_OUT_DATE", dateFormat.format(checkOut));
		return namedParameterJdbcTemplate.query(SQL_QUERY_FILTER_APARTMENT1, params, new ApartmentFilterRowMapper());
	}

	private static final class ApartmentFilterRowMapper implements RowMapper<ApartmentFilter> {
		@Override
		public ApartmentFilter mapRow(ResultSet rs, int rowNum) throws SQLException {
			ApartmentFilter apartmentFilter = new ApartmentFilter();
			apartmentFilter.setId(rs.getInt("AP_ID"));
			apartmentFilter.setDescription(rs.getString("AP_DESCRIPTION"));
			apartmentFilter.setGuestCount(rs.getInt("AP_GUEST_COUNT"));
			apartmentFilter.setCostPerDay(rs.getInt("AP_COST_PER_DAY"));
			ApartmentType apartmentType = new ApartmentType();
			apartmentType.setApartmentType(rs.getString("APARTMENT_TYPE_TITLE"));
			apartmentFilter.setApartmentType(apartmentType);
			City city = new City();
			city.setName(rs.getString("CITY_NAME"));
			apartmentFilter.setCity(city);
			return apartmentFilter;
		}

	}

}

package com.epam.apartmentBooking.model;

/**
 * Created by Siarhei_Kazei on 4/7/2017.
 */
public class ApartmentType {
	private long id;
	private String apartmentType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(String apartmentType) {
		this.apartmentType = apartmentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartmentType == null) ? 0 : apartmentType.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApartmentType other = (ApartmentType) obj;
		if (apartmentType == null) {
			if (other.apartmentType != null)
				return false;
		} else if (!apartmentType.equals(other.apartmentType))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApartmentType{" + "id=" + id + ", apartmentType='" + apartmentType + '\'' + '}';
	}
}

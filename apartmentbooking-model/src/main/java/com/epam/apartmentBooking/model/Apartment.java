package com.epam.apartmentBooking.model;

public class Apartment {

	private long id;
	private String name;
	private String description;
	private int guestCount;
	private int costPerDay;
	private long apartmentTypeId;
	private long cityId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}

	public int getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}

	public long getApartmentTypeId() {
		return apartmentTypeId;
	}

	public void setApartmentTypeId(long apartmentTypeId) {
		this.apartmentTypeId = apartmentTypeId;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Apartment apartment = (Apartment) o;

		if (id != apartment.id)
			return false;
		if (guestCount != apartment.guestCount)
			return false;
		if (costPerDay != apartment.costPerDay)
			return false;
		if (apartmentTypeId != apartment.apartmentTypeId)
			return false;
		if (cityId != apartment.cityId)
			return false;
		if (name != null ? !name.equals(apartment.name) : apartment.name != null)
			return false;
		return description != null ? description.equals(apartment.description) : apartment.description == null;

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + guestCount;
		result = 31 * result + costPerDay;
		result = 31 * result + (int) (apartmentTypeId ^ (apartmentTypeId >>> 32));
		result = 31 * result + (int) (cityId ^ (cityId >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Apartment{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", guestCount=" + guestCount + ", costPerDay=" + costPerDay + ", apartmentTypeId=" + apartmentTypeId + ", cityId=" + cityId + '}';
	}
}

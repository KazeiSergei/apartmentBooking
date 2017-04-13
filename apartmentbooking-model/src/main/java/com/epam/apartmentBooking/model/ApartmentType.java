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
    public String toString() {
        return "ApartmentType{" +
                "id=" + id +
                ", apartmentType='" + apartmentType + '\'' +
                '}';
    }
}

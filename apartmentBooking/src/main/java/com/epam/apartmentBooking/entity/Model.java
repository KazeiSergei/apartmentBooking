package com.epam.apartmentBooking.entity;

public abstract class Model {

	private Long id;

	public Model() {
	}

	public Model(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

package com.example.springCheckBox.domain;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = -2077443008943275545L;
	
	private String postCode;
	
	private String location;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

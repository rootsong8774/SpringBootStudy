package com.example.springCheckBox.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Member implements Serializable {

	private static final long serialVersionUID = 1710069820531371155L;

	private String userId;
	private String password;

	private String userName;

	private String email;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	private boolean foreigner;
	private String developer;
	private String nationality;

	private Address address;

	private List<Card> cardList;

	private String cars;
	private String[] carArray;
	private List<String> carList;

	private String introduction;


	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String[] getHobbyArray() {
		return hobbyArray;
	}

	public void setHobbyArray(String[] hobbyArray) {
		this.hobbyArray = hobbyArray;
	}

	public List<String> getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(List<String> hobbyList) {
		this.hobbyList = hobbyList;
	}

	public boolean isForeigner() {
		return foreigner;
	}

	public void setForeigner(boolean foreigner) {
		this.foreigner = foreigner;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public String getCars() {
		return cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	public String[] getCarArray() {
		return carArray;
	}

	public void setCarArray(String[] carArray) {
		this.carArray = carArray;
	}

	public List<String> getCarList() {
		return carList;
	}

	public void setCarList(List<String> carList) {
		this.carList = carList;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

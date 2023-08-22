package com.transportApp.places.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "places")
public class Places {

	@Id
	private String placeName;
	private String city;
	private String country;
	private String image;
	public Places() {
		super();
	}
	public Places(String placeName, String city, String country, String image) {
		super();
		this.placeName = placeName;
		this.city = city;
		this.country = country;
		this.image = image;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Places [placeName=" + placeName + ", city=" + city + ", country=" + country + ", image=" + image + "]";
	}
	
	
	
}

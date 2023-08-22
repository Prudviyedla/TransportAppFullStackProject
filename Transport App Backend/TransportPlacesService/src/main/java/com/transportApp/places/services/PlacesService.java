package com.transportApp.places.services;



import java.util.List;

import com.transportApp.places.model.Places;


public interface PlacesService {

	public Places addPlaces(Places places);
	public Places findByPlaceNames(String placeName);
	public List<Places> getAllPlaces();
	
}

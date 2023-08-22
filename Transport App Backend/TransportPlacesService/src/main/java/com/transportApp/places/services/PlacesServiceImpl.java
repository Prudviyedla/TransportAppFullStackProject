package com.transportApp.places.services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportApp.places.model.Places;
import com.transportApp.places.repository.PlacesRepository;


@Service
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	PlacesRepository placesRepository;
	
	@Override
	public Places findByPlaceNames(String placeName) {
		
		return placesRepository.findByPlaceName(placeName);
		
	}

	@Override
	public Places addPlaces(Places places) {
		return placesRepository.save(places);
	}

	@Override
	public List<Places> getAllPlaces() {
		return placesRepository.findAll();
	}

}

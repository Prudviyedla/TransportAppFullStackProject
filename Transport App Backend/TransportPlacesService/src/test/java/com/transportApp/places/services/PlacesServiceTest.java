package com.transportApp.places.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transportApp.places.model.Places;
import com.transportApp.places.repository.PlacesRepository;

@ExtendWith(MockitoExtension.class)
class PlacesServiceTest {

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	private PlacesRepository placesRepo;

	// @InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	private PlacesServiceImpl placesService;
	
	Places place;
	List<Places> placesList;
	
	@BeforeEach
	public void setUp() {
		place = new Places("Taj Mahal","Agra","India","tajMahal");
	}
	
	@AfterEach
	public void tearDown() {
		place=null;
		placesList=null;
	}
	
	@Test
	public void givenPlaceNameShouldReturnPlace() {
		when(placesRepo.findByPlaceName(place.getPlaceName())).thenReturn(place);
		placesService.findByPlaceNames(place.getPlaceName());
		verify(placesRepo,times(1)).findByPlaceName(place.getPlaceName());
	}

	@Test
	public void givenPlacesShouldReturnSavedPlaces() {
		when(placesRepo.save(any())).thenReturn(place);
		placesService.addPlaces(place);
		verify(placesRepo,times(1)).save(any());
	}
	
	@Test
	public void getAllPlacesShouldReturnAllPlaces() {
		when(placesRepo.findAll()).thenReturn(placesList);
		placesList = placesService.getAllPlaces();
		verify(placesRepo,times(1)).findAll();
	}
}

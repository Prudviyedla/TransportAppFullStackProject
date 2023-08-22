package com.transportApp.places.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transportApp.places.model.Places;
import com.transportApp.places.services.PlacesService;

@ExtendWith(MockitoExtension.class)
class PlacesControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private PlacesService placesService;
	
	@InjectMocks
	private PlacesController placesController;
	
	Places place;
	List<Places> placesList;
	
	@BeforeEach
	public void setUp() {
		place = new Places("Taj Mahal","Agra","India","tajMahal");
		mockMvc = MockMvcBuilders.standaloneSetup(placesController).build();
	}

	@AfterEach
	public void tearDown() {
		place=null;
		placesList=null;
	}
	
	@Test
	public void givenplacesShouldReturnAddedPlaces() throws Exception {
		when(placesService.addPlaces(any())).thenReturn(place);
		mockMvc.perform(post("/api/v1/places")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(place)))
				.andExpect(status().isCreated());
		verify(placesService,times(1)).addPlaces(any());
	}
	
	@Test
	public void givenPlaceNameShouldReturnPlace() throws Exception {
		when(placesService.findByPlaceNames(place.getPlaceName())).thenReturn(place);
		mockMvc.perform(get("/api/v1/places/Taj Mahal")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(place)))
				.andExpect(status().isOk());
		verify(placesService,times(1)).findByPlaceNames(place.getPlaceName());
	}
	
	@Test
	public void getAllPlaces() throws Exception {
		when(placesService.getAllPlaces()).thenReturn(placesList);
		mockMvc.perform(get("/api/v1/places")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(place)))
				.andExpect(status().isOk());
		verify(placesService,times(1)).getAllPlaces();
	}
	
	 public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return  new ObjectMapper().writeValueAsString(obj);
	    }
}

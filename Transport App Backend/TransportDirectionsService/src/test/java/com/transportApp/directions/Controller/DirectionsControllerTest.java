package com.transportApp.directions.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.transportApp.directions.Model.Directions;
import com.transportApp.directions.Model.DirectionsSearching;
import com.transportApp.directions.Service.DirectionsService;

@ExtendWith(MockitoExtension.class)
class DirectionsControllerTest {

	@Autowired 
	private MockMvc mockMvc;
	
	@Mock
	private DirectionsService dirService;
	
	@InjectMocks
	private DirectionsController dirController;
	
	Directions dir;
	DirectionsSearching dirs;
	List<Directions> dirList;
	
	@BeforeEach
	public void setUp() {
		dir = new Directions(1,"Vijayawada","Guntur",40,"Bus",60,40);
		dirs = new DirectionsSearching(dir.getOrigin(),dir.getDestination());
		mockMvc = MockMvcBuilders.standaloneSetup(dirController).build();
	}
	
	@AfterEach
	public void tearDown() {
		dir=null;
		dirList=null;
	}

	@Test
	public void givenDirectionsShouldReturnAddedDirections() throws Exception {
		when(dirService.addDirections(any())).thenReturn(dir);
		mockMvc.perform(post("/api/v1/directions/addDirection")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dir)))
				.andExpect(status().isCreated());
		verify(dirService,times(1)).addDirections(any());
	}
	
	@Test
	public void givenOriginAndDestinationShouldReturnUserIds() throws Exception {
		List<Integer> dirIds = new ArrayList<>();
		when(dirService.findByOringinAndDestination(dirs.getOrigin(), dirs.getDestination())).thenReturn(dirIds);
		mockMvc.perform(get("/api/v1/directions/specifiedDirections")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dirs)))
				.andExpect(status().isOk());
		verify(dirService,times(1)).findByOringinAndDestination(dirs.getOrigin(), dirs.getDestination());
	}
	
	@Test
	public void givenGetAllDirectionsShouldReturnAllUsers() throws  Exception {
		when(dirService.getAllDirections()).thenReturn(dirList);
		mockMvc.perform(get("/api/v1/directions/allDirections")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dir)))
				.andExpect(status().isOk());
		verify(dirService,times(1)).getAllDirections();
	}
	
	 public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return  new ObjectMapper().writeValueAsString(obj);
	    }
	 
}

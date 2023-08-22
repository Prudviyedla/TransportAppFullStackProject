package com.transportApp.directions.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.transportApp.directions.Model.Directions;
import com.transportApp.directions.Repository.DirectionsRepository;

@ExtendWith(MockitoExtension.class)
class DirectionsServiceTest {

	//@Mock annotation is used to create the mock object to be injected
	@Mock
	private DirectionsRepository dirRepo;
	
	//@InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	private DirectionsServiceImpl dirService;
	
	private Directions dir,dir2;
	private List<Directions> dirList;
	
	@BeforeEach
	public void setUp() {
		dir = new Directions(1,"Vijayawada","Guntur",40,"Bus",60,40);
		dir2 = new Directions(2,"Vijayawada","Guntur",40,"Train",40,50);
	}
	
	@AfterEach
	public void tearDown() {
		dir=null;
		dirList=null;
	}
	
	@Test
	public void givenDirectionsShouldReturnSavedDirections() {
		when(dirRepo.save(any())).thenReturn(dir);
		dirService.addDirections(dir);
		verify(dirRepo,times(1)).save(any());
	}
	
	@Test
	public void givenGetAllDirectionsShoulsReturnAllUsers() {
		dirRepo.save(dir);
		dirRepo.save(dir2);
		when(dirRepo.findAll()).thenReturn(dirList);
		dirList = dirService.getAllDirections();
		verify(dirRepo,times(1)).save(dir2);
		verify(dirRepo,times(1)).findAll();
	}

	@Test
	public void givenOriginAndDestinationShouldReturnId() {
		List<Integer> dirIds = dirService.findByOringinAndDestination(dir.getOrigin(), dir.getDestination());
		verify(dirRepo,times(1)).findByOriginAndDestination(dir.getOrigin(), dir.getDestination());
	}
	
}

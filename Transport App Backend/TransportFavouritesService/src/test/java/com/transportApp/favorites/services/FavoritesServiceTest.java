package com.transportApp.favorites.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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

import com.transportApp.favorites.model.Favorites;
import com.transportApp.favorites.model.FavoritesComposite;
import com.transportApp.favorites.repository.FavoritesRepository;

@ExtendWith(MockitoExtension.class)
class FavoritesServiceTest {

	//@Mock annotation is used to create the mock object to be injected
	@Mock
	FavoritesRepository favRepository;
	
	//@InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	FavoritesServiceImpl favService;

	FavoritesComposite fC,fc2;
	Favorites fav,fav2;
	List<Favorites> favList;
	
	@BeforeEach
	public void setUp() {
		fC = new FavoritesComposite(1,"Mumbai");
		fav = new Favorites(fC);
		fc2 = new FavoritesComposite(1,"Kolkata");
		fav2 = new Favorites(fc2);
	}
	@AfterEach
	public void tearDown() {
		fav=fav2=null;
		favList=null;
	}
	
	@Test
	public void testAddToFavSucces() {
		when(favRepository.save(any())).thenReturn(fav);
		favService.addToFavorites(fav);
		verify(favRepository,times(1)).save(any());
	}
	
	@Test
	public void testAddToFavFailure() {
		when(favRepository.save(any())).thenReturn(null);
		favService.addToFavorites(fav);
		verify(favRepository,times(1)).save(any());		
	}
	
	@Test
	public void testGetAllFav() {
		favRepository.save(fav);
		favRepository.save(fav2);
		when(favRepository.findAll()).thenReturn(favList);
		favList = favService.getAll();
		verify(favRepository,times(1)).save(fav);
		verify(favRepository,times(1)).findAll();
	}
	
	@Test
	public void givenFavoritesToDeleteThenShouldDeleteTheFavorites() {
		Favorites addedFav = favService.removeFromFavorites(any());
		verify(favRepository,times(1)).delete(any());
	}
	
	@Test
	public void givenUserIdThenShouldReturnFavoritePlaces() {
		favRepository.save(fav);
		favRepository.save(fav2);
		when(favRepository.findByFavoritesCompositeUserId(fC.getUserId())).thenReturn(favList);
		favList = favService.findByFavoritesCompositeUserId(fC.getUserId());
		verify(favRepository,times(1)).findByFavoritesCompositeUserId(fC.getUserId());
	}
}

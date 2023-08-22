package com.transportApp.favorites.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transportApp.favorites.model.Favorites;
import com.transportApp.favorites.model.FavoritesComposite;
import com.transportApp.favorites.services.FavoritesService;

@ExtendWith(MockitoExtension.class)
class FavoritesControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;

	@Mock
	private FavoritesService favService;
	
	@InjectMocks
	private FavoritesController favController;
	
	FavoritesComposite fc1;
	Favorites fav1;
	List<Favorites> favList;
	
	@BeforeEach
	public void setUp() {
		fc1=new FavoritesComposite(1,"Agra");
		fav1=new Favorites(fc1);
		mockMvc=MockMvcBuilders.standaloneSetup(favController).build();	
	}

	@AfterEach
	public void tearDown() {
		fav1=null;
		favList=null;
	}
	
	@Test
	public void givenFavoritesShouldReturnSavedFavorites() throws Exception {
		when(favService.addToFavorites(any())).thenReturn(fav1);
		mockMvc.perform(post("/api/v1/favorites")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fav1)))
				.andExpect(status().isCreated());
		verify(favService,times(1)).addToFavorites(any());
	}
	
	@Test
	public void getListOfFavorites() throws  Exception {
		when(favService.getAll()).thenReturn(favList);
		mockMvc.perform(get("/api/v1/favorites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fav1)))
                .andExpect(status().isAccepted())
                .andDo(print());
        verify(favService,times(1)).getAll();		
	}

	@Test	
	public void givenUserIdReturnListOfUserFavorites() throws Exception {
		favList = favService.findByFavoritesCompositeUserId(fav1.getFavoritesComposite().userId);
		when(favService.findByFavoritesCompositeUserId(fav1.getFavoritesComposite().getUserId())).thenReturn(favList);
		mockMvc.perform(get("/api/v1/favorites/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fav1)))
                .andExpect(status().isOk())
                .andDo(print());
		verify(favService,times(2)).findByFavoritesCompositeUserId(fav1.getFavoritesComposite().userId);
	}
	
	@Test
	public void givenUserIdAndFavoritePlaceToDeleteFavoritePlace() throws Exception {
		when(favService.removeFromFavorites(any())).thenReturn(fav1);
		mockMvc.perform(delete("/api/v1/favorites/deleteFav/1/Agra")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fav1)))
                .andExpect(status().isOk())
                .andDo(print());
		verify(favService,times(1)).removeFromFavorites(any());
	}
	
	 public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return  new ObjectMapper().writeValueAsString(obj);
	    }
}

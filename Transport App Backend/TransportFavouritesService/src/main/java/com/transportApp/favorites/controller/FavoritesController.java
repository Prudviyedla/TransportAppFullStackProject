package com.transportApp.favorites.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transportApp.favorites.model.Favorites;
import com.transportApp.favorites.model.FavoritesComposite;
import com.transportApp.favorites.services.FavoritesService;


@RestController
@RequestMapping("api/v1/favorites")
//@CrossOrigin(origins = "*")
public class FavoritesController {

	@Autowired
	FavoritesService favoriteService;
	
	//Create Fav
	@PostMapping
	public ResponseEntity<Favorites> addToFavorites(@RequestBody FavoritesComposite favoritesComposite ){
		Favorites favorites = new Favorites(favoritesComposite);
		return new ResponseEntity<Favorites>(favoriteService.addToFavorites(favorites), HttpStatus.CREATED);
	}
	
	//Find Favs
	@GetMapping("/{userId}")
	public ResponseEntity<List<String>> getUserFavorites(@PathVariable Integer userId){
		List<FavoritesComposite> userFavoriteComposites = new ArrayList();
		List<Favorites> userFavorites = favoriteService.findByFavoritesCompositeUserId(userId);
		for (Favorites favorites : userFavorites) {
			userFavoriteComposites.add(favorites.getFavoritesComposite());
		}
		List<String> userFavoritePlaces = new ArrayList<>();
		for (FavoritesComposite favoritesComposite : userFavoriteComposites) {
			userFavoritePlaces.add(favoritesComposite.getFavPlace());
		}
		return new ResponseEntity<List<String>>(userFavoritePlaces ,HttpStatus.OK);
	}
	// Get all favorites
	@GetMapping
	public ResponseEntity<List<Favorites>> getAllFavorites(){
		List<Favorites> favList = favoriteService.getAll();
		return new ResponseEntity<List<Favorites>>(favList,HttpStatus.ACCEPTED);
	}
	
	//Delete Fav
	@DeleteMapping("/deleteFav/{userId}/{favPlace}")
	public ResponseEntity<Favorites> deleteFavorites(@PathVariable Integer userId, @PathVariable String favPlace){
		Favorites favorites = new Favorites(new FavoritesComposite(userId, favPlace));
		Favorites returnedFavorites = favoriteService.removeFromFavorites(favorites);
		return new ResponseEntity<Favorites>(returnedFavorites ,HttpStatus.OK);
	}
	
}

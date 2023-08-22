package com.transportApp.favorites.services;


import java.util.List;

import com.transportApp.favorites.model.Favorites;


public interface FavoritesService {
	public List<Favorites> findByFavoritesCompositeUserId(Integer userId);
	public Favorites addToFavorites(Favorites favorites);
	public Favorites removeFromFavorites(Favorites favorites);
	List<Favorites> getAll();
}
package com.transportApp.favorites.services;


import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportApp.favorites.model.Favorites;
import com.transportApp.favorites.repository.FavoritesRepository;


@Service
//@Transactional
public class FavoritesServiceImpl implements FavoritesService {
	
	@Autowired
	FavoritesRepository favoritesRepository;

	@Override
	public Favorites addToFavorites(Favorites favorites) {
		return favoritesRepository.save(favorites);
	}

	@Override
	public List<Favorites> findByFavoritesCompositeUserId(Integer userId) {
		List<Favorites> reqFavorites = favoritesRepository.findByFavoritesCompositeUserId(userId);
		return reqFavorites;
	}

	@Override
	public Favorites removeFromFavorites(Favorites favorites) {
		favoritesRepository.delete(favorites);
		return favorites;
	}


	@Override
	public List<Favorites> getAll() {
		return favoritesRepository.findAll();
	}

}

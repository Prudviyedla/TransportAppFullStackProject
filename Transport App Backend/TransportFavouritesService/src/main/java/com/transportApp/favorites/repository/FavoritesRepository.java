package com.transportApp.favorites.repository;



import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.transportApp.favorites.model.Favorites;
import com.transportApp.favorites.model.FavoritesComposite;


@Repository
public interface FavoritesRepository extends MongoRepository<Favorites, FavoritesComposite> {
	List<Favorites> findByFavoritesCompositeUserId(Integer userId);
}

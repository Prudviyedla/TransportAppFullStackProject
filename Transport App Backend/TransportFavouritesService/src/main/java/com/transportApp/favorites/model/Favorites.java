package com.transportApp.favorites.model;


import org.springframework.data.annotation.Id;

//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "favorites")
public class Favorites {


//	@EmbeddedId
	@Id
	private FavoritesComposite favoritesComposite;

	
	public Favorites() {
		super();
	}

	
	public Favorites(FavoritesComposite favoritesComposite) {
		super();
		this.favoritesComposite = favoritesComposite;
	}

	public FavoritesComposite getFavoritesComposite() {
		return favoritesComposite;
	}

	public void setFavoritesComposite(FavoritesComposite favoritesComposite) {
		this.favoritesComposite = favoritesComposite;
	}

	@Override
	public String toString() {
		return "Favorites [favoritesComposite=" + favoritesComposite + "]";
	}
	
	
	
}

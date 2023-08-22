package com.transportApp.favorites.model;

import java.io.Serializable;
import java.util.Objects;

//import javax.persistence.Embeddable;


//@Embeddable
public class FavoritesComposite implements Serializable {

	public Integer userId;

	public String favPlace;

	public FavoritesComposite() {
		super();
	}

	public FavoritesComposite(Integer userId, String favPlace) {
		super();
		this.userId = userId;
		this.favPlace = favPlace;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFavPlace() {
		return favPlace;
	}

	public void setFavPlace(String favPlace) {
		this.favPlace = favPlace;
	}

//	@Override
//	public String toString() {
//		return "FavoritesComposite [userId=" + userId + ", favPlace=" + favPlace + "]";
//	}

	@Override
	public int hashCode() {
		return Objects.hash(favPlace, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoritesComposite other = (FavoritesComposite) obj;
		return Objects.equals(favPlace, other.favPlace) && Objects.equals(userId, other.userId);
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(favPlace, userId);
//	}
	

	
		
	
	
	
}

package com.transportApp.places.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.transportApp.places.model.Places;

@Repository
public interface PlacesRepository extends MongoRepository<Places, String> {

	public Places findByPlaceName(String placeName);
	
}

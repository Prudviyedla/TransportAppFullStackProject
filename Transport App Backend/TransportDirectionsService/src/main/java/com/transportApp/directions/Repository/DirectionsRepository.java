package com.transportApp.directions.Repository;


import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.transportApp.directions.Model.Directions;

@Repository
public interface DirectionsRepository extends MongoRepository<Directions, Integer>{
	public List<Directions> findByOriginAndDestination(String origin, String destination);
}

package com.transportApp.directions.Service;


import java.util.List;

import com.transportApp.directions.Model.Directions;


public interface DirectionsService {
	
	public Directions addDirections(Directions directions);	
	public List<Integer> findByOringinAndDestination(String origin, String destination);
	public List<Directions> getAllDirections();
}

package com.transportApp.directions.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transportApp.directions.Model.Directions;
import com.transportApp.directions.Model.DirectionsSearching;
import com.transportApp.directions.Service.DirectionsService;


@RestController
@RequestMapping("api/v1/directions")
//@CrossOrigin(origins = "*")
public class DirectionsController {

	@Autowired
	DirectionsService directionsService;
	
	//Add Direction
	@PostMapping("/addDirection")
	public ResponseEntity<Directions> addDirections(@RequestBody Directions directions) {
		
		Directions addedDirections = directionsService.addDirections(directions);
		
		return new ResponseEntity<Directions>(addedDirections, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/specifiedDirections")
	public ResponseEntity<List<Integer>> getByOriginAndDestination(@RequestBody DirectionsSearching directionsSearching){
		
		List<Integer> specifiedIds = directionsService.findByOringinAndDestination(directionsSearching.getOrigin(), directionsSearching.getDestination());
		
		return new ResponseEntity<List<Integer>>(specifiedIds, HttpStatus.OK);
	}
	
	@GetMapping("/allDirections")
	public ResponseEntity<List<Directions>> getAllDirections(){
		return new ResponseEntity<List<Directions>>(directionsService.getAllDirections(), HttpStatus.OK);
	}
	
	
}

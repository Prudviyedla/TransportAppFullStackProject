package com.transportApp.directions.Model;



public class DirectionsSearching {
	
	
	private String origin;
	private String destination;
	
	
	public DirectionsSearching() {
		super();
	}

	public DirectionsSearching(String origin, String destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "DirectionsSearching [origin=" + origin + ", destination=" + destination + "]";
	}

}

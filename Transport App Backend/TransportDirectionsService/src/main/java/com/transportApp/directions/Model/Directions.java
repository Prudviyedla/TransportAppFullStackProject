package com.transportApp.directions.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "directions")
public class Directions {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dirId;
	private String origin;
	private String destination;
	private int distance;
	private String modeOfTransport;
	private int time;
	private int price;

	public Directions() {
		super();
	}

	public Directions(int dirId, String origin, String destination, int distance, String modeOfTransport, int time,
			int price) {
		super();
		this.dirId = dirId;
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.modeOfTransport = modeOfTransport;
		this.time = time;
		this.price = price;
	}

	public int getDirId() {
		return dirId;
	}

	public void setDirId(int dirId) {
		this.dirId = dirId;
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Directions [dirId=" + dirId + ", origin=" + origin + ", destination=" + destination + ", distance="
				+ distance + ", modeOfTransport=" + modeOfTransport + ", time=" + time + ", price=" + price + "]";
	}

}

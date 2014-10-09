package se.shadovo.whiteboard.messages;

import se.shadovo.whiteboard.util.BroadcastTo;


public class BroadcastingPackage {

	private final String data;
	private final BroadcastTo broadcastTo;
	private final String updatedLocation; // if null location is same as before
	
	public BroadcastingPackage(String data, BroadcastTo broadcastTo) {
		this(data, broadcastTo, null);
	}
	
	public BroadcastingPackage(String data, BroadcastTo broadcastTo, String location) {
		this.data = data;
		this.broadcastTo = broadcastTo;
		this.updatedLocation = location;
	}
	
	public String getData() {
		return data;
	}

	public BroadcastTo getBroadcastTo() {
		return broadcastTo;
	}

	public String getLocation() {
		return updatedLocation;
	}
}

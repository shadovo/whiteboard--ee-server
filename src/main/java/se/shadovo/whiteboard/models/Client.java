package se.shadovo.whiteboard.models;

import javax.websocket.Session;

public class Client {

	private final Session session;
	private String location;

	public Client(Session session) {
		this(session, null);
	}
	
	public Client(Session session, String location) {
		this.session = session;
		this.location = location;
	}

	public Session getSession() {
		return session;
	}

	public Object getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

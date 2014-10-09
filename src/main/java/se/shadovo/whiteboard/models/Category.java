package se.shadovo.whiteboard.models;

import java.util.List;



public final class Category implements Storable {

	private int id; 
	private final String name;
	private final int whiteboardId;
	private List<Postit> postits;
	
	public Category(int id, String name, int whiteboardId) {
		this.id = id;
		this.name = name;
		this.whiteboardId = whiteboardId;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int setIdIfNotExisting(int id) {
		if (this.id == -1) {			
			this.id = id;
		}
		return this.id;
	}

	public String getName() {
		return name;
	}

	public int getWhiteboardId() {
		return whiteboardId;
	}

	public void setPostits(List<Postit> postits) {
		this.postits = postits;
	}
	
	public List<Postit> getPostits() {
		return this.postits;
	}
}

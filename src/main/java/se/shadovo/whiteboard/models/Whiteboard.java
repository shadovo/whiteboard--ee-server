package se.shadovo.whiteboard.models;

import java.util.List;



public final class Whiteboard implements Storable {

	private int id = -1;
	private final String name;
	private List<Category> categories;

	public Whiteboard(int id, String name) {
		this.id = id;
		this.name = name;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}

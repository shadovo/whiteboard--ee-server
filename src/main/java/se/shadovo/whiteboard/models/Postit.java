package se.shadovo.whiteboard.models;


public final class Postit implements Storable {

	private int id;
	private final String title;
	private final String description;
	private final String color;
	private final int categoryId;
	
	public Postit(int id, String title, String description, String color, int categoryId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.color = color;
		this.categoryId = categoryId;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int setIdIfNotExisting(int id) {
		System.out.println("ID P: " + id);
		System.out.println("ID P2: " + this.id);
		if (this.id == -1) {			
			this.id = id;
		}
		return this.id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getColor() {
		return color;
	}

	public int getCategoryId() {
		return categoryId;
	}
	
}

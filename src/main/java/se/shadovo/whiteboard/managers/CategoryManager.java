package se.shadovo.whiteboard.managers;

import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Whiteboard;

public interface CategoryManager extends BasicManager<Category> {

	public List<Category> read(Whiteboard whiteboard);
	
}

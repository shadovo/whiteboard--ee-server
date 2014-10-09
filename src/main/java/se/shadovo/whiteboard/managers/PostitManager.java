package se.shadovo.whiteboard.managers;

import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Postit;

public interface PostitManager extends BasicManager<Postit> {

	public List<Postit> read(Category category);
	
}

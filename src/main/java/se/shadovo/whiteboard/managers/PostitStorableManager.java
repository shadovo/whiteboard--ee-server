package se.shadovo.whiteboard.managers;

import java.util.LinkedList;
import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Postit;

public class PostitStorableManager extends BasicStorableManager<Postit> implements PostitManager {

	@Override
	public List<Postit> read(Category category) {
		List<Postit> postits = new LinkedList<>();
		for (Postit postit : storage.values()) {
			if (category.getId() == postit.getCategoryId()) {
				postits.add(postit);
			}
		}
		return postits;
	}	
}

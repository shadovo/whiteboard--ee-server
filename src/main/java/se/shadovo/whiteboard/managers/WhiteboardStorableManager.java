package se.shadovo.whiteboard.managers;

import java.util.LinkedList;
import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Whiteboard;

public class WhiteboardStorableManager extends BasicStorableManager<Whiteboard> implements WhiteboardManager {

	private CategoryManager categoryManager;

	public WhiteboardStorableManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	@Override
	public Whiteboard create(Whiteboard whiteboard) {
		int id = lastId.incrementAndGet();
		whiteboard.setIdIfNotExisting(id);
		whiteboard.setCategories(null);
		return storage.put(id, whiteboard);
	}
	
	@Override
	public Whiteboard read(int id) {
		Whiteboard whiteboard = super.read(id);
		List<Category> cats = categoryManager.read(whiteboard);
		whiteboard.setCategories(cats);
		return whiteboard;
	}
	
	@Override
	public List<Whiteboard> read(List<Integer> ids) {
		
		List<Whiteboard> whiteboards = new LinkedList<>();
		for (int id : ids) {
			whiteboards.add(this.read(id));
		}
		
		return whiteboards;
	}
}

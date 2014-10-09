package se.shadovo.whiteboard.managers;

import java.util.LinkedList;
import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Whiteboard;


public class CategoryStorableManager extends BasicStorableManager<Category> implements CategoryManager {

	private final PostitManager postitManager;

	public CategoryStorableManager(PostitManager postitManager) {
		this.postitManager = postitManager;
	}
	
	@Override
	public Category create(Category category) {
		int id = lastId.incrementAndGet();
		category.setIdIfNotExisting(id);
		category.setPostits(null);
		return storage.put(id, category);
	}
	
	@Override
	public Category read(int id) {
		Category category = super.read(id);
		category.setPostits(postitManager.read(category));
		return category;
	}
	
//	@Override
//	public List<Category> read(List<Integer> ids) {
//		List<Category> categories = super.read(ids);
//		for (int id : ids) {
//			Category category = read(id);
//			categories.add(category);
//		}
//		return categories;
//	}
	
	@Override
	public List<Category> read(Whiteboard whiteboard) {
		List<Category> categories = new LinkedList<>();
		for (Category category : storage.values()) {
			if (whiteboard.getId() == category.getWhiteboardId()) {
				category.setPostits(postitManager.read(category));
				categories.add(category);
			}
		}
		return categories;
	}
}

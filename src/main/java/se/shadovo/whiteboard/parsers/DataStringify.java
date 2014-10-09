package se.shadovo.whiteboard.parsers;

import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Postit;
import se.shadovo.whiteboard.models.Whiteboard;

public interface DataStringify {
	public String buildPostits(String messageType, List<Postit> postits);
	public String buildCategories(String messageType, List<Category> categories);
	public String buildWhiteboards(String messageType, List<Whiteboard> whiteboards);
}

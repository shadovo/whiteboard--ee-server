package se.shadovo.whiteboard.parsers;

import java.util.List;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Postit;
import se.shadovo.whiteboard.models.Whiteboard;

public interface DataParser {
	public String parseMessageType(String message) throws Exception;
	public List<Integer> parseIds(String message) throws Exception;
	public List<Postit> parsePostits(String message) throws Exception;
	public List<Category> parseCategories(String message) throws Exception;
	public List<Whiteboard> parseWhiteboards(String message) throws Exception;
}

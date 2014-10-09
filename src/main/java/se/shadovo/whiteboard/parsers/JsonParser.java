package se.shadovo.whiteboard.parsers;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Postit;
import se.shadovo.whiteboard.models.Whiteboard;

public class JsonParser implements DataParser {

	@Override
	public String parseMessageType(String message) throws Exception {
		JsonObject json = asJsonObject(message);
		return json.getString("type");
	}

	@Override
	public List<Integer> parseIds(String message) throws Exception {
		JsonArray json = getMessageBody(message);
		System.out.println(json);
		List<Integer> ids = new LinkedList<>();
		for (int i = 0; i < json.size(); i = i + 1) {
			Integer id = json.getInt(i);
			ids.add(id);
		}
		return ids;
	}

	private Postit parsePostit(JsonObject json) throws Exception {
		System.out.println("POSTIT: " + json);
		System.out.println("json obj");
		int id = -1;
		if (json.containsKey("id")) {
			id = json.getInt("id");
		}
		System.out.println("ID: " + id);
		String title = json.getString("title");
		String description = json.getString("description");
		String color = json.getString("color");
		int categoryId = json.getInt("categoryId");
		System.out.println("got the values");
		return new Postit(id, title, description, color, categoryId);
	}

	@Override
	public List<Postit> parsePostits(String message) throws Exception {
		System.out.println("MESSAGE: " + message);
		JsonArray json = getMessageBody(message);
		System.out.println("getMessageBody");
		List<Postit> postits = new LinkedList<>();
		System.out.println("list created");
		for (int i = 0; i < json.size(); i = i + 1) {
			System.out.println("loop");
			Postit postit = parsePostit(json.getJsonObject(i));
			System.out.println("postit done!");
			
			postits.add(postit);
		}
		System.out.println("loop done");
		System.out.println("Parsed: " + postits);
		return postits;
	}

	private Category parseCategory(JsonObject json) throws Exception {
		int id = -1;
		if (json.containsKey("id")) {
			id = json.getInt("id");
		}
		String name = json.getString("name");
		int whiteboardId = json.getInt("whiteboardId");
		return new Category(id, name, whiteboardId);
	}

	@Override
	public List<Category> parseCategories(String message) throws Exception {
		JsonArray json = getMessageBody(message);
		List<Category> categories = new LinkedList<>();
		for (int i = 0; i < json.size(); i = i + 1) {
			Category category = parseCategory(json.getJsonObject(i));
			categories.add(category);
		}
		return categories;
	}

	private Whiteboard parseWhiteboard(JsonObject json) throws Exception {
		System.out.println("ping 2");
		int id = -1;
		System.out.println("ping 3");
		if (json.containsKey("id")) {
			id = json.getInt("id");
		}
		System.out.println("ping 4");
		String name = json.getString("name");
		System.out.println(name);
		return new Whiteboard(id, name);
	}

	@Override
	public List<Whiteboard> parseWhiteboards(String message) throws Exception {
		JsonArray json = getMessageBody(message);
		List<Whiteboard> whiteboards = new LinkedList<>();
		for (int i = 0; i < json.size(); i = i + 1) {
			Whiteboard whiteboard = parseWhiteboard(json.getJsonObject(i));
			whiteboards.add(whiteboard);;
		}
		System.out.println("BOOM 7");
		return whiteboards;
	}

	private JsonObject asJsonObject(String object) throws Exception {
		JsonReader jsonReader = Json.createReader(new StringReader(object));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject;
	}
	
	private JsonArray getMessageBody(String message) throws Exception {
		JsonObject json = asJsonObject(message);
		return json.getJsonArray("body");
	}
}

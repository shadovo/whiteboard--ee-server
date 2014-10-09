package se.shadovo.whiteboard.parsers;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import se.shadovo.whiteboard.models.Category;
import se.shadovo.whiteboard.models.Postit;
import se.shadovo.whiteboard.models.Whiteboard;

public class JsonStringify implements DataStringify {

	@Override
	public String buildPostits(String messageType, List<Postit> postits) {
		return buildMessage(messageType, buildPostits(postits));
	}

	@Override
	public String buildCategories(String messageType, List<Category> categories) {
		return buildMessage(messageType, buildCategories(categories));
	}

	@Override
	public String buildWhiteboards(String messageType,
			List<Whiteboard> whiteboards) {
		return buildMessage(messageType, buildWhiteboards(whiteboards));
	}
	
	private String buildMessage(String type, JsonArray body) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("type", type);
		job.add("body", body);
		String jsonString = job.build().toString();
		System.out.println(jsonString);
		return jsonString;
	}	
	
	private JsonObject buildPostit(Postit postit) {
		System.out.println("Building postit");
		JsonObjectBuilder job = Json.createObjectBuilder();
		if (postit != null) {
			job.add("id", 			postit.getId());
			job.add("title", 		postit.getTitle());
			job.add("description", 	postit.getDescription());
			job.add("color", 		postit.getColor());
			job.add("categoryId", 	postit.getCategoryId());
		}
		System.out.println("postit built");
		return job.build();
	}
	
	private JsonArray buildPostits(List<Postit> postits) {
		System.out.println("Building postits");
		JsonArrayBuilder jab = Json.createArrayBuilder();
		if (postits != null) {
			for (Postit postit : postits) {
				jab.add(buildPostit(postit));
			}
		}
		System.out.println("postits built");
		return jab.build();
	}


	private JsonObject buildCategory(Category category) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		if (category != null) {
			job.add("id", 			category.getId());
			job.add("name", 		category.getName());
			job.add("whiteboardId", category.getWhiteboardId());
			job.add("postits", 		buildPostits(category.getPostits()));
		}
		return job.build();
	}

	private JsonArray buildCategories(List<Category> categories) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		if (categories != null) {
			for (Category category : categories) {
				jab.add(buildCategory(category));
			}
		}
		return jab.build();
	}

	private JsonObject buildWhiteboard(Whiteboard whiteboard) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		if (whiteboard != null) {
			job.add("id", 			whiteboard.getId());
			job.add("name", 		whiteboard.getName());
			job.add("categories", 	buildCategories(whiteboard.getCategories()));
		}
		return job.build();
	}

	private JsonArray buildWhiteboards(List<Whiteboard> whiteboards) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		if (whiteboards != null) {
			for (Whiteboard whiteboard : whiteboards) {
				jab.add(buildWhiteboard(whiteboard));
			}
		}
		return jab.build();
	}
}

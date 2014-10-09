package se.shadovo.whiteboard.messages;

import java.util.List;

import se.shadovo.whiteboard.managers.CategoryManager;
import se.shadovo.whiteboard.managers.CategoryStorableManager;
import se.shadovo.whiteboard.managers.PostitManager;
import se.shadovo.whiteboard.managers.PostitStorableManager;
import se.shadovo.whiteboard.managers.WhiteboardManager;
import se.shadovo.whiteboard.managers.WhiteboardStorableManager;
import se.shadovo.whiteboard.models.Whiteboard;
import se.shadovo.whiteboard.parsers.DataParser;
import se.shadovo.whiteboard.parsers.DataStringify;
import se.shadovo.whiteboard.util.BroadcastTo;
import se.shadovo.whiteboard.util.WebSocketMessageTypeConsts;

public class MessageHandler {
	
	private DataParser dataParser;
	private DataStringify dataStringify;
	private PostitManager postitHandler = new PostitStorableManager();
	private CategoryManager categoryHandler = new CategoryStorableManager(postitHandler);
	private WhiteboardManager whiteboardHandler = new WhiteboardStorableManager(categoryHandler);

	public MessageHandler(DataParser dataParser, DataStringify dataStringify) {
		this.dataParser = dataParser;
		this.dataStringify = dataStringify;
		System.out.println("new message handler");
	}
	
	public BroadcastingPackage handle(String messageString) throws Exception {
		String body; 
		BroadcastTo broadcastTo;
		String location = null;
		
		String  messageType = dataParser.parseMessageType(messageString);
		switch (messageType) {
		//
		// Postits
		case WebSocketMessageTypeConsts.POSTIT_NEW:
			body = dataStringify.buildPostits(messageType, postitHandler.create(dataParser.parsePostits(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		case WebSocketMessageTypeConsts.POSTIT_GET:
			body = dataStringify.buildPostits(messageType, postitHandler.read(dataParser.parseIds(messageString)));
			broadcastTo = BroadcastTo.ME;
			break;
		case WebSocketMessageTypeConsts.POSTIT_GET_ALL:
			body = dataStringify.buildPostits(messageType, postitHandler.read());
			broadcastTo = BroadcastTo.ME;
			break;
		case WebSocketMessageTypeConsts.POSTIT_UPDATE:
			body =  dataStringify.buildPostits(messageType, postitHandler.update(dataParser.parsePostits(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		case WebSocketMessageTypeConsts.POSTIT_DELETE:
			body = dataStringify.buildPostits(messageType, postitHandler.delete(dataParser.parseIds(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		//	
		// Categories
		case WebSocketMessageTypeConsts.CATEGORY_NEW:
			body = dataStringify.buildCategories(messageType, categoryHandler.create(dataParser.parseCategories(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		case WebSocketMessageTypeConsts.CATEGORY_GET:
			body = dataStringify.buildCategories(messageType, categoryHandler.read(dataParser.parseIds(messageString)));
			broadcastTo = BroadcastTo.ME;
			break;
		case WebSocketMessageTypeConsts.CATEGORY_GET_ALL:
			body = dataStringify.buildCategories(messageType, categoryHandler.read());
			broadcastTo = BroadcastTo.ME;
			break;
		case WebSocketMessageTypeConsts.CATEGORY_UPDATE:
			body = dataStringify.buildCategories(messageType, categoryHandler.update(dataParser.parseCategories(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		case WebSocketMessageTypeConsts.CATEGORY_DELETE:
			body = dataStringify.buildCategories(messageType, categoryHandler.delete(dataParser.parseIds(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		//
		// Whiteboards	
		case WebSocketMessageTypeConsts.WHITEBOARD_NEW:
			body = dataStringify.buildWhiteboards(messageType, whiteboardHandler.create(dataParser.parseWhiteboards(messageString)));
			broadcastTo = BroadcastTo.ALL;
			break;
		case WebSocketMessageTypeConsts.WHITEBOARD_GET:
			List<Whiteboard> whiteboards = whiteboardHandler.read(dataParser.parseIds(messageString));
			body = dataStringify.buildWhiteboards(messageType, whiteboards);
			broadcastTo = BroadcastTo.ME;
			int whiteboardId = whiteboards.get(0).getId();
			location = "whiteboard-" + whiteboardId;
			break;
		case WebSocketMessageTypeConsts.WHITEBOARD_GET_ALL:
			body = dataStringify.buildWhiteboards(messageType, whiteboardHandler.read());
			broadcastTo = BroadcastTo.ME;
			location = "all-whiteboards";
			break;
		//
		// Default
		default:
			return new ErrorBroadcastingPackage();
		}	
		return new BroadcastingPackage(body, broadcastTo, location);
	}
}

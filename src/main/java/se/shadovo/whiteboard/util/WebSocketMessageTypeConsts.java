package se.shadovo.whiteboard.util;

public final class WebSocketMessageTypeConsts {

	// Postits
	public static final String POSTIT_NEW = "postit-new";
	public static final String POSTIT_GET = "postit-get";
	public static final String POSTIT_GET_ALL = "postits-get-all";
	public static final String POSTIT_UPDATE = "postit-update";
	public static final String POSTIT_DELETE = "postit-delete";
	
	// Categories
	public static final String CATEGORY_NEW = "category-new";
	public static final String CATEGORY_GET = "category-get";
	public static final String CATEGORY_GET_ALL = "category-get-all";
	public static final String CATEGORY_UPDATE = "category-update";
	public static final String CATEGORY_DELETE = "category-delete";
	
	// Whiteboards
	public static final String WHITEBOARD_NEW = "whiteboard-new";
	public static final String WHITEBOARD_GET = "whiteboard-get";
	public static final String WHITEBOARD_GET_ALL = "whiteboard-get-all";

	// User
	public static final String USER_CONNECT_TO_WHITEBOARD = "user-connect-to-whiteboard";
	public static final String USER_DISCONNECT_FROM_WHITEBOARD = "user-disconect-from-whiteboard";
	
	private WebSocketMessageTypeConsts() {
		throw new AssertionError();
	}
}

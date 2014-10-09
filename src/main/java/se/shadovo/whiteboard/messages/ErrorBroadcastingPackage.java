package se.shadovo.whiteboard.messages;

import se.shadovo.whiteboard.util.BroadcastTo;

public class ErrorBroadcastingPackage extends BroadcastingPackage {
	
	public ErrorBroadcastingPackage() {
		super("error", BroadcastTo.ME);
	}
}

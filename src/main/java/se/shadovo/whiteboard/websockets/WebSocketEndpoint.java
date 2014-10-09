package se.shadovo.whiteboard.websockets;

import java.io.IOException;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import se.shadovo.whiteboard.managers.ClientHandler;
import se.shadovo.whiteboard.messages.BroadcastingPackage;
import se.shadovo.whiteboard.messages.MessageHandler;
import se.shadovo.whiteboard.models.Client;
import se.shadovo.whiteboard.parsers.JsonParser;
import se.shadovo.whiteboard.parsers.JsonStringify;

@ServerEndpoint("/ws")
public class WebSocketEndpoint {

	private static MessageHandler messageHandler = new MessageHandler(new JsonParser(), new JsonStringify());

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected...");
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Closing...");
		closeSession(session);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("Error: " + throwable);
		closeSession(session);
	}

	@OnMessage
	public void onMessage(Session session, String messageString) throws Exception {
		System.out.println("<- " + messageString);
		BroadcastingPackage bp = messageHandler.handle(messageString);
		ClientHandler.updateClientLocation(session, bp.getLocation());
		sendResponse(session, bp);
	}

	private void closeSession(Session session) {
		ClientHandler.removeClient(session);
		System.out.println("...session closed");
	}
	
	private void sendResponse(Session session, BroadcastingPackage bp) throws IOException {
		List<Client> clients = ClientHandler.getClientsToBradcastTo(session, bp.getBroadcastTo());
		broadcast(bp.getData(), clients);
	}
	
	private void broadcast(String messageString, List<Client> clients) {
		System.out.println("-> " + messageString);
		synchronized (this) {
			for (Client client : clients) {
				if (client.getSession() != null && client.getSession().isOpen()) {
					client.getSession().getAsyncRemote().sendText(messageString);
				}
			}
		}
	}
}

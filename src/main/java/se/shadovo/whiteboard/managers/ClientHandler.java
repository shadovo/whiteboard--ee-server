package se.shadovo.whiteboard.managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import se.shadovo.whiteboard.models.Client;
import se.shadovo.whiteboard.util.BroadcastTo;

public class ClientHandler {

	private static Map<String, Client> clients = Collections
			.synchronizedMap(new HashMap<String, Client>());

	private ClientHandler() {
	}

	public static List<Client> getClientsToBradcastTo(Session session,
			BroadcastTo broadcastTo) {

		List<Client> clientsToBroadcastTo = new LinkedList<>();

		switch (broadcastTo) {
		case ME:
			clientsToBroadcastTo.add(getClientBySession(session));
			break;
		case OTHERS:
			clientsToBroadcastTo = getOtherClientsAtSameLocationAs(session);
			break;
		case ALL:
			clientsToBroadcastTo = getAllClientsAtSameLocationAs(session);
			break;
		}
		return clientsToBroadcastTo;
	}

	public static Client updateClientLocation(Session session, String location) {
			Client client;
			if (clients.containsKey(session.getId())) {
				client = clients.get(session.getId());
				if (location != null) {
					client.setLocation(location);
				}
			} else {
				if (location == null) {
					location = "no-location-specified";
				}
				client = new Client(session, location);
				clients.put(session.getId(), client);
			}
			return client;
	}

	public static void removeClient(Session session) {
		clients.remove(session.getId());
	}

	private static Client getClientBySession(Session session) {
		if (clients.containsKey(session.getId())) {
			return clients.get(session.getId());
		}
		return null;
	}

	private static List<Client> getOtherClientsAtSameLocationAs(Session session) {
		return getClientsAtSameLocationAs(session, true);
	}

	private static List<Client> getAllClientsAtSameLocationAs(Session session) {
		return getClientsAtSameLocationAs(session, false);
	}

	private static List<Client> getClientsAtSameLocationAs(Session session,
			boolean excludeSelf) {
		Client client = getClientBySession(session);
		List<Client> cls = new LinkedList<>();
		for (Client cl : clients.values()) {
			if (!(excludeSelf && client.equals(cl))) {
				if ((client.getLocation()).equals(cl.getLocation())) {
					cls.add(cl);
				}
			}
		}
		return cls;
	}

}

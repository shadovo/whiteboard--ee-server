package se.shadovo.whiteboard.managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import se.shadovo.whiteboard.models.Storable;

public abstract class BasicStorableManager<T extends Storable>  {

	protected  Map<Integer, T> storage = Collections.synchronizedMap(new HashMap<Integer, T>());
	protected final AtomicInteger lastId = new AtomicInteger(0);
	
	public T create(T storable) {
		int id = lastId.incrementAndGet();
		storable.setIdIfNotExisting(id);
		return storage.put(id, storable);
	}
	
	public List<T> create(List<T> storables) {
		List<T> newlyCreatedStorables = new LinkedList<T>();
		for (T storable : storables) {
			storable = this.create(storable);
			newlyCreatedStorables.add(storable);
		}
		return storables;
	}
	
	public List<T> read() {
		List<T> storables = new LinkedList<>();
		for (T storable : storage.values()) {
			storables.add(storable);
		}
		return storables;
	}
	
	public T read(int id) {
		if (storage.containsKey(id)) {
			return storage.get(id);
		}
		return null;
	}
	
	public List<T> read(List<Integer> ids) {
		List<T> storables = new LinkedList<T>();
		for (int id : ids) {
			T storable = read(id);
			if (storable != null) {
				storables.add(storable);
			}
		}
		return storables;
	}
	
	public T update(T storable) {
		if (storage.containsKey(storable.getId())) {
			storage.put(storable.getId(), storable);
			return storable;
		}
		return null;
		
	}
	public List<T> update(List<T> storables) {
		List<T> updatedStorable = new LinkedList<T>();
		for (T storable : storables) {
			storable = update(storable);
			if (storable != null) {
				updatedStorable.add(storable);
			}
		}
		return updatedStorable;
	}
	
	public T delete(int id) {
		if (storage.containsKey(id)) {
			return storage.remove(id);
		}
		return null;
	}
	
	public List<T> delete(List<Integer> ids) {
		List<T> storables = new LinkedList<>();
		for (int id: ids) {
			T storable = delete(id);
			if (storable != null) {
				storables.add(storable);
			}
		}
		return storables;
 	}

}

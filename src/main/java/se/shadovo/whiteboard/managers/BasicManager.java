package se.shadovo.whiteboard.managers;

import java.util.List;

import se.shadovo.whiteboard.models.Storable;

public interface BasicManager<T extends Storable> {

	public T create(T obj);
	public List<T> create(List<T> objs);
	public T read(int id);
	public List<T> read();
	public List<T> read(List<Integer> ids);
	public T update(T obj);
	public List<T> update(List<T> objs);
	public T delete(int id);
	public List<T> delete(List<Integer> ids);
	
}

package mycollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {

	private HashMap<Integer,Value<T>> map;
	
	private Value<T> prev;
	
	private Value<T> head;
	
	public LinkedHashSet () {
		map = new HashMap <Integer,Value<T>>();
	}
	
	private static class Value<T> {
		
		Integer prevHash;
		Integer nextHash;
		T obj;
		
		Value (T obj){
			this.obj = obj;
		}
	}
	
	private class LinkedHashSetIterator implements Iterator<T> {

		Value<T> current = head;
		int counter = 0;
		
		@Override
		public boolean hasNext() {
			return counter<size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}		
			Value<T> value;
			if(counter == 0) {
				value = current;
			} else {
				value = map.get(current.nextHash);
			}
			current = value;
			counter++;
			return value.obj;
		}
	}
	
	@Override
	public boolean add(T element) {
		boolean res = false;
		Value<T> addedValue = new Value<T>(element);
		if (map.putIfAbsent(element.hashCode(), addedValue)==null) {
			if (prev != null) {
				addedValue.prevHash=prev.obj.hashCode();
				prev.nextHash = element.hashCode();
			} else head = addedValue;
			prev = addedValue;
			size++;
			res = true;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Integer patternsHash = pattern.hashCode();
		Value<T> removedValue = map.get(patternsHash);
		if (removedValue!=null) {
			
			map.get(removedValue.prevHash).nextHash = removedValue.nextHash;
			map.get(removedValue.nextHash).prevHash = removedValue.prevHash;
			if (map.remove(patternsHash)!=null) {
				res = true;
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return map.containsKey(pattern.hashCode());
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		return map.get(pattern.hashCode()).obj;
	}
}

package mycollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {

	private HashMap<T,Value<T>> map;
	
	private Value<T> prev;
	
	private Value<T> head;
	
	public LinkedHashSet () {
		map = new HashMap <T,Value<T>>();
	}
	
	private static class Value<T> {
		
		T prevT;
		T nextT;
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
				value = map.get(current.nextT);
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
		if (map.putIfAbsent(element, addedValue)==null) {
			if (prev != null) {
				addedValue.prevT=prev.obj;
				prev.nextT = element;
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
		
		Value<T> removedValue = map.get(pattern);
		if (removedValue!=null) {
			
			map.get(removedValue.prevT).nextT = removedValue.nextT;
			map.get(removedValue.nextT).prevT = removedValue.prevT;
			if (map.remove(pattern)!=null) {
				res = true;
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return map.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		return map.get(pattern).obj;
	}
}

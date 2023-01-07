package mycollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {

	boolean add (T element);
	boolean remove(T pattern);
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while(it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}
	boolean isEmpty();
	int size();
	boolean contains(T pattern);

	default T[] toArray (T[] array) {
		if (array.length < size()) {
			array = Arrays.copyOf(array, size());
		}
		Iterator<T> it = iterator();
		int i =0;
		while(it.hasNext()) { 
			array[i++] = it.next();		
		}
		Arrays.fill(array, size(), array.length, null);
		return array;
	}
}

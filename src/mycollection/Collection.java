package mycollection;

import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {

	boolean add(T element);
	boolean remove (T pattern);
	boolean removeIf(Predicate<T> predicate);
	int size();
	int indexOf(T pattern);

	T[] toArray (T[]ar);
	
	default  void checkIndex(int index, boolean sizeIncluded) {
		int sizeDelta = sizeIncluded ? 0 : 1;
		if (index < 0 || index > size() - sizeDelta) {
			throw new IndexOutOfBoundsException(index);
		}
		
	}
	default boolean contains(T pattern) {	
		return indexOf(pattern) > -1;
	}
	
	default boolean isEmpty() {		
		return size() == 0;
	}
}

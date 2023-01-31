package mycollection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	
	default Stream<T> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}
	default Stream<T> parallelStream() {
		return StreamSupport.stream(this.spliterator(), true);
	}
	
	
	default T[] toArrayShuffling(T[] array) {
		Random r = new Random();
		return this.stream()
				.sorted((a, b) -> a.hashCode()*(r.nextInt(-100, 100)) - b.hashCode()*(r.nextInt(-100, 100)))
				.collect(Collectors.toList())
				.toArray(array);
	}
}

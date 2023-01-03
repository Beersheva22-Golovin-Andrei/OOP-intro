package mycollection;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {

	static final int DEFAULT_CAPACITY = 16;
	private T [] array;
	private int size;
	
	private class ArrayListIterator implements Iterator<T> {
		int currentIndex = 0;
			@Override
			public boolean hasNext() {	
				return currentIndex<size;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
			}
				return get(currentIndex++);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[])new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		if(size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index>0) {
			deleteElement(index);
			res = true;
			size--;
		}
		return res;
	}
	
	private void checkIndex (int index) {
		if (index<0 || index >= size) throw new IndexOutOfBoundsException();
	}
	
	private void deleteElement (int index) {
		System.arraycopy(array, index + 1, array, index, array.length - index - 1);
	}


	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		for (int i = 0; i < size; ) {
			if (predicate.test(array[i])) {
				deleteElement(i);
				res = true;
				size--;
			} else i++;
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return indexOf(pattern)>-1;
	}

	@Override
	public T[] toArray(T[] ar) {
		
		 if (ar.length < size) {
			 ar = Arrays.copyOf(ar, size);
		 }
		 System.arraycopy(array, 0, ar, 0, size);
	    if (ar.length> size) {
	    	ar[size]=null;
	    }
		return ar;
	}

	@Override
	public void add(int index, T element) {		
		if (index<0 || index>size) throw new IndexOutOfBoundsException();
		if (size==array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index]=element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index);
		T oldElement = array[index];
			deleteElement (index);
			size--;
		return oldElement;
	}

	@Override
	public int indexOf(T pattern) {
	int res =-1;
	int i=0;
	while (i<size && res<0) {
		if (array[i] == null ? array[i] == pattern : array[i].equals(pattern)) {
			res=i;
		}
		i++;
	}
		return res;	
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res =-1;
		int i=size-1;
		while (i>=0 && res<0) {
			if (array[i] == null ? array[i] == pattern : array[i].equals(pattern)) {
				res=i;
			}
			i--;
		}
			return res;	
		}

	@Override
	public T get(int index) {
		checkIndex(index);	
		return (T)array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index);
		array[index]=element;
	}
	
	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}

}

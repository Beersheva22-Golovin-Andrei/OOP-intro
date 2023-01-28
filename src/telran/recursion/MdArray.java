package telran.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	
	private MdArray<T>[] array;
	private T value;
	private static int[]dimen;
	public MdArray(int dimensions[], T value) {
		this(dimensions, 0, value);
	}
	@SuppressWarnings("unchecked")
	public MdArray(int[] dimensions, int firstDim, T value) {
		dimen = dimensions;
		if (firstDim == dimensions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for(int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimensions, firstDim + 1, value);
			}
		}
	}
	
	public void forEach (Consumer<T> cons) {
		for (MdArray<T> mD : array) {
			if (mD.array == null) {
				cons.accept(mD.value);
			} else {
				mD.forEach(cons);
			}
		}
	}
	
	public T[] toArray (T[] arr) {
		
		List<T> list = new ArrayList<>();
		this.forEach(el->list.add(el));
		return list.toArray(arr);
	}
	
	public T getValue (int[]indexes) {
		return findMd(indexes).value;
	}
	
	private void checkIndexes (int [] indexes) {
		if (dimen.length>indexes.length) throw new IllegalStateException();
		if (dimen.length<indexes.length) throw new NoSuchElementException();
	}
	
	private MdArray<T> findMd (int [] indexes){
		checkIndexes(indexes);
		MdArray<T> temp = this;
		int i = 0;
		while (temp.value==null) {
			if (dimen[i]<indexes[i]) throw new IndexOutOfBoundsException();
			temp = temp.array[indexes[i++]];
		}
			return temp;
		
	}
	
	public void setValue (int[]indexes, T newValue) {
		findMd(indexes).value=newValue;
	}
}

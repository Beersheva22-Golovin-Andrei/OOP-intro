package telran.myarrays;

import java.util.Arrays;
import java.util.Comparator;

public class MyArrays {

	static public <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		}while(moveMaxAtEnd(objects, length, comparator));
		
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for(int i = 0; i < length; i++) {
			if(comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
				res = true;
			}
		}
		return res;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;
		
	}


	public static <T> int binarySearch (T[] array, int searchedNumber, Comparator<T> comp) throws ClassCastException {

		int res=0;
		int left = 0;
		int right = array.length-1;
		int mid = (left + right)/2;		
		if (array[mid] instanceof Number) {			
			int compRes=1;
			while (left <= right && compRes!=0) {
        		compRes = comp.compare(array[mid], (T)(Integer.valueOf(searchedNumber)));        	
            if (compRes < 0)
            	left = mid + 1;
            else if (compRes > 0)
            	right = mid - 1;
            else
                res = mid;
        }
		if (compRes != 0) res=-(left+1);
		} else throw new ClassCastException();			
			return res;
    }
	

	public static <T> int binarySearch (T[] array, T searchedNumber, Comparator<T> comp) {

		int res=0;
		int left = 0;
		int right = array.length-1;
		int mid = (left + right)/2;
		int compRes = 1;
        while (left <= right && compRes!=0) {
        	compRes = comp.compare(array[mid], searchedNumber);
        	if (compRes < 0)
            	left = mid + 1;
            else if (compRes > 0)
            	right = mid - 1;
            else
                res = mid;
        }
        if (compRes != 0) res=-(left+1);		
		return res;
    }
	
	public static <T extends Number> int binarySearch (T[] array, int searchedNumber) {

		int res=0;
		int left = 0;
		int right = array.length-1;
		int mid = (left + right)/2;		
		
			int compRes=1;
			while (left <= right && compRes!=0) {
				
				Integer tempInt = array[mid].intValue();
            if (tempInt < searchedNumber)
            	left = mid + 1;
            else if (tempInt > searchedNumber)
            	right = mid - 1;
            else
                res = mid;
        }
		if (compRes != 0) res=-(left+1);	
			return res;

}
}


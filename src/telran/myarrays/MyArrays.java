package telran.myarrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

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
	public static <T> T[] filter(T[] objects, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(objects, predicate);
		
		T[] res = Arrays.copyOf(objects, countPredicate);
		int index = 0;
		for(T element: objects) {
			if(predicate.test(element)) {
				res[index++] = element;
			}
		}			
		return res;
	}


	private static <T> int getCountPredicate(T[] objects, Predicate<T> predicate) {
		int res = 0;
		
		for(T element: objects) {
			if(predicate.test(element)) {
				res++;
			}
		}
		return res;
	}
	
	public static <T> T[] removeIf(T[] objects, Predicate<T> predicate) {	
		
		T[] res = filter(objects, predicate.negate());
		return res;
	}
	
	public static <T> T[] removeRepeated(T[] objects) {
		int countUniqElement = countUniqElement(objects);
		T[] workArray = Arrays.copyOf(objects, objects.length);
		T[] res = Arrays.copyOf(objects, countUniqElement);
		int index = 0;
		while(workArray.length > 0) {
			T element = workArray[0];
			res[index++] = workArray[0];
			Predicate<T> predicate = t -> t == element;
			workArray = removeIf(workArray, predicate);	
		}
		return res;
	}

	private static <T> int countUniqElement(T[] objects) {
		int res = 0;
		T[] temp = Arrays.copyOf(objects, objects.length);
		while(temp.length > 0) {
			T element = temp[0];
			Predicate<T> predicate = t -> t == element;
			temp = removeIf(temp, predicate);
			res++;
		}
		return res;
	}
	
	public static <T> boolean  contains(T[] objects, T pattern) {
		int index = 0;
		while (index < objects.length && !isEquals(objects[index],pattern)) {
				index++;
			}
		return index < objects.length;
	}
	
private static boolean isEquals(Object element, Object pattern) {
		
		return element == null ? element == pattern : element.equals(pattern);
	}
	
	public static <T> String  join(T[] objects, String delimetr) {
		String res = "";
		if (objects.length > 0) {
			StringBuilder builder = new StringBuilder(objects[0].toString());
			for (int i = 1; i < objects.length; i++) {
				builder.append(delimetr).append(objects[i]);
			}
			res = builder.toString();
		}
		return res;
	}	

}


package telran.recursion;

public class LinearRecursion {
	
	static public long factorial(int n) {
		long res = 0;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2){
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}
	
	static public int power(int a, int b) {
		int res;
		if (b<0) {
			throw new IllegalArgumentException();
		} else if (b==0) {
			res = 1;
		} else {
			res = multip(a, power(a, b-1));
		}		
		return res;
	}
	
	public static int multip (int a, int n) {
		int res;
		if (n<0) {
			res = multip (a, -n);
		} else if (n==0) {
			res = 0;
		}else {
		res=a+multip(a, n-1);
		}
		return res;
	}
	
	
	static public long sumArr(int ar[]) {
		return sumArr(0, ar);
	}
	private static long sumArr(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sumArr(firstIndex + 1, ar);
		}
		return res;
	}
	
	
	public static long square(int x) {

		long res;
		if (x<0) {
			res = 1;
		} else {
			res = x - 1 + square(x - 1) +x ;
		}
		
		return res;
	}
	
	
	
	public static void reverse(int ar[]) {

		reverse(0, ar.length - 1, ar);
	}
	private static void reverse(int firstIndex, int lastIndex, int[] ar) {
		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}
		
	}
	private static void swap(int[] ar, int firstIndex, int lastIndex) {
		int tmp = ar[firstIndex];
		ar[firstIndex] = ar[lastIndex];
		ar[lastIndex] = tmp;
		
	}
	
	public static boolean isSubstring(String str, String substr) {
		boolean res = false;
		if (str.length() >= substr.length()) {
			res = isEqual(str, substr) ? true : isSubstring(str.substring(1), substr);
		} 

		return res;

	}
	private static boolean isEqual(String str, String substr) {
		boolean res = false;
		if (substr.length() == 0) {
			res = true;
		} else if (str.charAt(0) == substr.charAt(0)) {
			res = isEqual(str.substring(1), substr.substring(1));
		}
		
		return res;
	}

}

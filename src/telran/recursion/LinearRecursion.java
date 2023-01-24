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
	
	public static boolean isSubstring(String string, String subString) {
		boolean res = false;
		
		if (string.length() > 0) {
			if (subString.charAt(0) == string.charAt(0)) {
				String str = subString;
				
					if(isSub(string, str) == 0) {
						res = res | isSubstring(string.substring(1), subString);
					} else res = true;
					
			} else {
				res = res |isSubstring(string.substring(1), subString);
			}
				
		}
		return res;
	}

	private static int isSub(String string, String str) {
		int res = 0;
		if (str.length() > 0) {
			if (string.charAt(0) == str.charAt(0)) {
				res = res | isSub(string.substring(1), str.substring(1));
			}  
		} else {
			res = 1;
		}
		return res;
	
	}

}

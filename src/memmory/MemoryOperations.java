package memmory;

public class MemoryOperations {
	
	
	public static int getMaxAvaliableMemory() {
		int left = 0;
		boolean running = true;
		int right = Integer.MAX_VALUE;
		
		byte[] arr;
		int res = (left + right)/2;
				
		while(res > 0 && running) {
			
			try {
				arr = null;
				arr = new byte[res];
				try {
					arr=null;
					arr = new byte[res + 1];	
					left = res;
					res = left + (right-left)/2;
				} catch (Throwable t) {
					running = false;			
				}			
			} catch (Throwable t) {
			right = res;
			res = (left + right)/2;
			}			
		}
		return res;
	}
}



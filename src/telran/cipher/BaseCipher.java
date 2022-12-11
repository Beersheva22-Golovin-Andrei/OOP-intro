package telran.cipher;



public class BaseCipher {
	
	private static final int LOW_BORDER = 33;
	private static final int UPPER_BORDER = 126;
	
	private String key;

	public BaseCipher(int length) {
		
		if (length<2) length = 2;
		if (length>93) length = 93;
	
		boolean [] helper = new boolean [UPPER_BORDER-LOW_BORDER];
		char [] seq = new char [length];
		for (int i=0; i<seq.length;) {			
			int ch = (int)(LOW_BORDER+Math.random()*(UPPER_BORDER-LOW_BORDER));
			if (!helper[ch-LOW_BORDER]) {			
				seq[i]=(char)ch;
				helper[ch-LOW_BORDER]=true;
				i++;
		}	
	}
		this.key=new String(seq);
	}


	public String cipher (int number) {
		
		char [] keyArr = this.key.toCharArray();	
		StringBuilder res = new StringBuilder("");
		
		do {
			res.append(number%keyArr.length+" ");
			number = number/keyArr.length;
		
		}while (number>=keyArr.length);	
		
		res.append(number);		
		String [] resArr = res.toString().split(" ");
		char [] encodedNumberArr = new char[resArr.length];
		
		for (int i = 0; i<resArr.length; i++) {
			encodedNumberArr[resArr.length-1-i]=keyArr[Integer.parseInt(resArr[i])];
		}		
		return new String(encodedNumberArr);
	}
	
	
	public int decipher (String encodeNumber) {
		
		char [] encodeArr = encodeNumber.toCharArray();
		int keyLength = this.key.length();
		int res = key.indexOf(encodeArr[0])*keyLength + key.indexOf(encodeArr[1]);
		for (int i =1; i<encodeArr.length-1; i++) {
		res =res*keyLength + key.indexOf(encodeArr[i+1]); 
		}
		return res;
	}
	
}

package telran.cipher.test;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;

public class BaseCipherTest {
	
	@Test
	public void cipher() {
		int number = 56646515;
		BaseCipher baseCip = new BaseCipher (91);
		System.out.println();
		String encode = baseCip.cipher(number);
		System.out.println("encode - "+ encode);
		System.out.println();
		System.out.println("decode - "+baseCip.decipher(encode));
	}

}

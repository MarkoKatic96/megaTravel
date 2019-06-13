package com.megatravel.agentglobalback;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class Singleton {
	private static Singleton instance = null;
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		
		return instance;
	}
	
	
	public static String getRandomString() {
		String chrs = "0123456789abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = null;
        String customTag = "";
		try {
			secureRandom = SecureRandom.getInstanceStrong();
			customTag = secureRandom.ints(128, 0, chrs.length()).mapToObj(i -> chrs.charAt(i))
				      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
			return customTag;
		} catch (NoSuchAlgorithmException e) {
			customTag = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().getMostSignificantBits();
			return customTag;
		}
	}
}

package com.developer.user.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Utils {
	private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789";
	
    public int generateUserId(int length) {
        return generateRandomString(length);
    }
    
    private int generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return Integer.parseInt(new String(returnValue));
    }

}

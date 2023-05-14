package org.example.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.KeySpec;

public class KeyService {

    public Key getKey(String password) {

        try {
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), "salt".getBytes(), 42, 256);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return new SecretKeySpec(keyFactory.generateSecret(keySpec).getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения ассинхронного ключа: ", e);
        }

    }
}
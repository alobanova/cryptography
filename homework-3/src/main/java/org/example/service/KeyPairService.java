package org.example.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyPairService {

    public KeyPair getKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения ассинхронного ключа: ", e);
        }

    }
}
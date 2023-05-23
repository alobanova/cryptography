package org.example.service;

import org.example.model.KeystoreType;
import org.example.model.RandomMethod;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class RandomService {
    private static final String SHA1PRNG = "SHA1PRNG";

    public KeystoreType getRandomKeystoreType(RandomMethod method) {
        KeystoreType keystoreType;
        switch (method) {
            case BASIC:
                keystoreType = getPredicationByBasicMethod();
                break;
            case SECURE:
                keystoreType = getPredicationBySecureMethod();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
        return keystoreType;
    }

    private KeystoreType getPredicationByBasicMethod() {
        int position = new Random().nextInt(KeystoreType.values().length);
        return KeystoreType.values()[position];
    }

    private KeystoreType getPredicationBySecureMethod() {
        try {
            SecureRandom random = SecureRandom.getInstance(SHA1PRNG);
            random.setSeed(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            int position = random.nextInt(KeystoreType.values().length);
            return KeystoreType.values()[position];
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка создания SecureRandom: ", ex);
        }
    }
}

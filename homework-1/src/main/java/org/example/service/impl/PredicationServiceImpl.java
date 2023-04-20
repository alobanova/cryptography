package org.example.service.impl;

import org.example.model.Predication;
import org.example.model.PredicationMethod;
import org.example.service.PredicationService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class PredicationServiceImpl implements PredicationService {
    private static final String SEED_VARIABLE_NAME = "SEED";
    private static final String SHA1PRNG = "SHA1PRNG";

    @Override
    public String getPredication(String name, PredicationMethod method) throws NoSuchAlgorithmException {
        Predication predication;
        switch (method) {
            case BASIC:
                predication = getPredicationByBasicMethod();
                break;
            case SECURE:
                predication = getPredicationBySecureMethod();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
        return String.format("Привет %s! %s", name, predication.message);
    }

    private Predication getPredicationByBasicMethod() {
        int position = new Random().nextInt(Predication.values().length);
        return Predication.values()[position];
    }

    private Predication getPredicationBySecureMethod() throws NoSuchAlgorithmException {
        String seed = System.getenv(SEED_VARIABLE_NAME);
        SecureRandom random = SecureRandom.getInstance(SHA1PRNG);
        if (seed != null) {
            random.setSeed(seed.getBytes());
        } else {
            random.setSeed(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        }
        int position = random.nextInt(Predication.values().length);
        return Predication.values()[position];
    }
}

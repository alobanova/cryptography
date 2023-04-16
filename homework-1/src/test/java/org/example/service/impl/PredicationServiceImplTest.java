package org.example.service.impl;


import org.example.model.PredicationMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class PredicationServiceImplTest {
    @Test
    void mustGetPredicationByBasicMethod() throws NoSuchAlgorithmException {
        String name = "TestName";

        PredicationServiceImpl service = new PredicationServiceImpl();

        String result = service.getPredication(name, PredicationMethod.BASIC);

        Assertions.assertNotNull(result);
    }

    @Test
    void mustGetPredicationBySecureMethod() throws NoSuchAlgorithmException {
        String name = "TestName";

        PredicationServiceImpl service = new PredicationServiceImpl();

        String result = service.getPredication(name, PredicationMethod.SECURE);

        Assertions.assertNotNull(result);
    }
}

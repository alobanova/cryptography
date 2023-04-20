package org.example;

import org.example.model.PredicationMethod;
import org.example.service.PredicationService;
import org.example.service.impl.PredicationServiceImpl;

import java.security.NoSuchAlgorithmException;

public class Application {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        PredicationService service = new PredicationServiceImpl();
        String predication  = service.getPredication(args[0], PredicationMethod.valueOf(args[1]));
        System.out.println(predication);
    }
}

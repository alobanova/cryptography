package org.example.service;

import org.example.model.PredicationMethod;

import java.security.NoSuchAlgorithmException;

public interface PredicationService {

    String getPredication(String name, PredicationMethod method) throws NoSuchAlgorithmException;
}

package org.example.common.service.impl;

import org.example.service.DigestService;

import java.security.MessageDigest;

public class DigestServiceImpl implements DigestService {
    private static final String SHA_256 = "SHA-256";

    @Override
    public byte[] getDigest(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            return messageDigest.digest(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}

package org.example.service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureService {
    public byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(data);
            return sign.sign();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания подписи: ", e);
        }
    }

    public boolean verify(byte[] data, byte[] digitalSignature, PublicKey publicKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(publicKey);
            sign.update(data);
            return sign.verify(digitalSignature);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания подписи: ", e);
        }
    }
}
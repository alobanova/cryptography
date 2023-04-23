package org.example.service.impl;

import org.example.service.EncryptionService;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

public class EncryptionServiceImpl implements EncryptionService {
    private static final String AES_CBC_PKCS_5_PADDING = "AES/CBC/PKCS5Padding";

    private final Key secretKey;

    public EncryptionServiceImpl(Key secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}

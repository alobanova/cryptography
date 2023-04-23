package org.example.service.impl;

import org.example.service.DecryptionService;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

public class DecryptionServiceImpl implements DecryptionService {

    private final Key secretKey;

    public DecryptionServiceImpl(Key secretKey) {
        this.secretKey = secretKey;
    }

    private static final String AES_CBC_PKCS_5_PADDING = "AES/CBC/PKCS5Padding";

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}

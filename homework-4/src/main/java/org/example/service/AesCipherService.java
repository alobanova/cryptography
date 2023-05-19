package org.example.service;

import javax.crypto.Cipher;
import java.security.Key;

public class AesCipherService {

    public byte[] encrypt(byte[] data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка шифрования: ", e);
        }
    }

    public byte[] decrypt(byte[] data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка расшифрования: ", e);
        }
    }
}
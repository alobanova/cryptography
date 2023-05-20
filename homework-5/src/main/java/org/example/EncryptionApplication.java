package org.example;

import org.example.service.KeyService;
import org.example.service.RsaCipherService;

import java.security.KeyPair;
import java.util.Base64;

public class EncryptionApplication {

    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        String keystoreName = args[0];
        int keyLength = Integer.parseInt(args[1]);

        KeyService keyService = new KeyService();
        KeyPair key = keyService.createAsyncKey(keyLength, keystoreName);

        RsaCipherService cipherService = new RsaCipherService();

        byte[] encryptedMessage = cipherService.encrypt("Java".getBytes(), key.getPublic());

        byte[] base64EncryptedMessage = Base64.getEncoder().encode(encryptedMessage);
        System.out.println("Сообщение Java успешно зашифровано. " + new String(base64EncryptedMessage));

    }
}

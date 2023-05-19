package org.example;

import org.example.service.KeyService;
import org.example.service.AesCipherService;

import java.security.Key;

public class EncryptionApplication {

    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        String password = args[0];

        KeyService keyService = new KeyService();
        Key key = keyService.getKey(password);

        AesCipherService cipherService = new AesCipherService();

        String world = args[1];
        byte[] encryptedMessage = cipherService.encrypt(world.getBytes(), key);

        System.out.printf("Сообщение %s успешно зашифровано\n", world);

        byte[] decryptedMessage = cipherService.decrypt(encryptedMessage, key);

        System.out.printf("Сообщение %s успешно расшифровано\n", new String(decryptedMessage));
    }
}

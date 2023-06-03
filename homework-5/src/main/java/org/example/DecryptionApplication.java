package org.example;

import org.example.service.KeyService;
import org.example.service.RsaCipherService;

import java.security.Key;
import java.util.Base64;

public class DecryptionApplication {

    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
        String keystoreName = args[0];
        String keyName = args[1];
        String base64EncryptedMessage = args[2];

        byte[] encryptedMessage = Base64.getDecoder().decode(base64EncryptedMessage);
        KeyService keyService = new KeyService();
        Key key = keyService.getKey(keyName, keystoreName);

        RsaCipherService cipherService = new RsaCipherService();

        byte[] decryptedMessage = cipherService.decrypt(encryptedMessage, key);

        System.out.println("Сообщение успешно расшифровано. " + new String(decryptedMessage));

    }
}

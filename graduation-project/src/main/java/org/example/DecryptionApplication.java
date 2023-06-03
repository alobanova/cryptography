package org.example;

import org.example.model.KeystoreType;
import org.example.service.KeyService;
import org.example.service.RsaCipherService;
import org.example.service.SignatureService;

import java.security.Key;
import java.security.KeyPair;
import java.util.Base64;

public class DecryptionApplication {

    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Не переданы обязательные параметры");
            return;
        }
        String keystoreName = args[0];
        String keystorePassword = args[1];
        String base64EncryptedMessage = args[2];
        String base64Signature = args[3];
        String keyName = args[4];

        byte[] encryptedMessage = Base64.getDecoder().decode(base64EncryptedMessage);
        byte[] signature = Base64.getDecoder().decode(base64Signature);

        KeyService keyService = new KeyService();
        KeyPair key = keyService.getKey(keystoreName, keystorePassword, keyName);

        RsaCipherService cipherService = new RsaCipherService();
        SignatureService signatureService = new SignatureService();

        byte[] decryptedMessage = cipherService.decrypt(encryptedMessage, key.getPrivate());
        boolean isCorrect = signatureService.verify(encryptedMessage, signature, key.getPublic());

        System.out.println("Сообщение успешно расшифровано. " + new String(decryptedMessage));
        if (isCorrect) {
            System.out.println("Sign is ok");
        } else {
            System.out.println("Sign is not ok");
        }
    }
}

package org.example;

import org.example.model.KeystoreType;
import org.example.model.RandomMethod;
import org.example.service.KeyService;
import org.example.service.RandomService;
import org.example.service.RsaCipherService;
import org.example.service.SignatureService;

import java.security.KeyPair;
import java.util.Base64;

public class EncryptionApplication {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Не переданы обязательные параметры");
            return;
        }
        RandomMethod randomMethod = RandomMethod.valueOf(args[0]);
        String keystorePassword = args[1];
        String word = args[2];

        RandomService randomService = new RandomService();
        KeystoreType keystoreType = randomService.getRandomKeystoreType(randomMethod);
        KeyService keyService = new KeyService();
        KeyPair key = keyService.createKeyPair(keystoreType, keystorePassword);

        RsaCipherService cipherService = new RsaCipherService();
        SignatureService signatureService = new SignatureService();

        byte[] encryptedMessage = cipherService.encrypt(word.getBytes(), key.getPublic());
        byte[] base64EncryptedMessage = Base64.getEncoder().encode(encryptedMessage);

        byte[] signature = signatureService.sign(encryptedMessage, key.getPrivate());
        byte[] base64Signature = Base64.getEncoder().encode(signature);

        System.out.println("Тип keystore: " + keystoreType);
        System.out.println("Сообщение успешно зашифровано. " + new String(base64EncryptedMessage));
        System.out.println("Сообщение успешно подписано. " + new String(base64Signature));

    }
}

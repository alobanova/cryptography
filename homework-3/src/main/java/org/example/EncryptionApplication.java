package org.example;

import org.example.service.KeyPairService;
import org.example.service.RsaCipherService;
import org.example.service.SignatureService;

import java.io.IOException;
import java.security.KeyPair;

public class EncryptionApplication {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }
        KeyPairService keyPairService = new KeyPairService();
        KeyPair keyPair = keyPairService.getKeyPair();

        RsaCipherService cipherService = new RsaCipherService();
        SignatureService signatureService = new SignatureService();

        String world = args[0];
        byte[] encryptedMessage = cipherService.encrypt(world.getBytes(), keyPair.getPublic());
        byte[] signature = signatureService.sign(encryptedMessage, keyPair.getPrivate());

        System.out.printf("Сообщение %s успешно зашифровано и подписано\n", world);

        byte[] decryptedMessage = cipherService.decrypt(encryptedMessage, keyPair.getPrivate());
        Boolean isCorrect = signatureService.verify(encryptedMessage, signature, keyPair.getPublic());
        System.out.printf("Сообщение %s успешно расшифровано\n", new String(decryptedMessage));
        if (isCorrect) {
            System.out.println("Sign is ok");
        } else {
            System.out.println("Sign is not ok");
        }
    }
}

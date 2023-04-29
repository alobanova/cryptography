package org.example;

import org.example.common.service.SecretKeyService;
import org.example.common.service.impl.DigestServiceImpl;
import org.example.common.service.impl.SecretKeyServiceImpl;
import org.example.service.DigestService;
import org.example.service.EncryptionService;
import org.example.service.impl.ClientSocketServiceImpl;
import org.example.service.impl.EncryptionServiceImpl;

import java.io.IOException;
import java.security.Key;

public class EncryptionApplication {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }
        SecretKeyService secretKeyService = new SecretKeyServiceImpl();
        Key secretKey = secretKeyService.getSecretKey();

        EncryptionService service = new EncryptionServiceImpl(secretKey);
        DigestService digestService = new DigestServiceImpl();

        String world = args[0];
        byte[] hash = digestService.getDigest(world.getBytes());
        byte[] encryptedMessage = service.encrypt(world.getBytes());

        System.out.printf("Сообщение %s успешно зашифровано \n", world);

        try (ClientSocketServiceImpl socketService = new ClientSocketServiceImpl();) {
            socketService.sendMessage(hash);
            socketService.sendMessage(encryptedMessage);
        }
        System.out.println("Зашифрованное сообщение и его хэш отправлены получателю");
    }
}

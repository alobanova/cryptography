package org.example;

import org.example.common.service.SecretKeyService;
import org.example.common.service.impl.DigestServiceImpl;
import org.example.common.service.impl.SecretKeyServiceImpl;
import org.example.service.DecryptionService;
import org.example.service.DigestService;
import org.example.service.ServerSocketService;
import org.example.service.impl.DecryptionServiceImpl;
import org.example.service.impl.ServerSocketServiceImpl;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;

public class DecryptionApplication {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }
        SecretKeyService secretKeyService = new SecretKeyServiceImpl();
        Key key = secretKeyService.getSecretKey();
        DecryptionService service = new DecryptionServiceImpl(key);
        DigestService digestService = new DigestServiceImpl();

        ServerSocketService socket = new ServerSocketServiceImpl();

        System.out.println("Ожидание зашифрованных сообщений");
        byte[] expectedHash = socket.getMessage();
        byte[] encryptMessage = socket.getMessage();


        byte[] decryptedMessage = service.decrypt(encryptMessage);
        byte[] actualHash = digestService.getDigest(decryptedMessage);

        System.out.println("Расшифрованное сообщение: " + new String(decryptedMessage));
        System.out.println("Результат сравнения хэша сообщения: " + Arrays.equals(expectedHash, actualHash));

    }
}

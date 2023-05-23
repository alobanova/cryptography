package org.example.service;

import org.example.model.KeystoreType;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class KeyService {
    private static final String KEYSTORE_NAME = "keystore";

    public KeyPair createKeyPair(KeystoreType keystoreType, String keystorePassword) {
        try {
            char[] password = keystorePassword.toCharArray();
            KeyStore keyStore = KeyStore.getInstance(keystoreType.name());
            keyStore.load(null, password);
            CertAndKeyGen generator = new CertAndKeyGen("RSA", "SHA1WithRSA");
            generator.generate(2048);
            PrivateKey key = generator.getPrivateKey();
            X509Certificate cert = generator.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);
            X509Certificate[] chain = new X509Certificate[1];
            chain[0] = cert;
            keyStore.setKeyEntry("KeyName", key, password, chain);

            keyStore.store(new FileOutputStream(KEYSTORE_NAME + getKeystoreExtension(keystoreType)), password);
            return new KeyPair(cert.getPublicKey(), key);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания пары ключей: ", e);
        }
    }

    public KeyPair getKey(String keystoreName, String keystorePassword, String keyName) {
        try {
            char[] password = keystorePassword.toCharArray();
            KeyStore keyStore = KeyStore.getInstance(new File(keystoreName), password);
            Key key = keyStore.getKey(keyName, password);
            X509Certificate cert = (X509Certificate)keyStore.getCertificate(keyName);
            return new KeyPair(cert.getPublicKey(), (PrivateKey) key);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения пары ключей: ", e);
        }
    }

    private String getKeystoreExtension(KeystoreType keystoreType) {
        String extension = "";
        switch (keystoreType) {
            case JKS:
                extension = ".jks";
                break;
            case JCEKS:
                extension = ".jceks";
                break;
        }
        return extension;
    }

}
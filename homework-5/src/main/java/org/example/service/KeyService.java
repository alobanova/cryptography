package org.example.service;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class KeyService {

    public KeyPair createAsyncKey(int keyLength, String keystoreName) {
        try {
            char[] password = "changeit".toCharArray();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, password);
            CertAndKeyGen generator = new CertAndKeyGen("RSA", "SHA1WithRSA");
            generator.generate(keyLength);
            PrivateKey key = generator.getPrivateKey();
            X509Certificate cert = generator.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);
            X509Certificate[] chain = new X509Certificate[1];
            chain[0] = cert;
            keyStore.setKeyEntry("keyName", key, password, chain);
            keyStore.store(new FileOutputStream(keystoreName), password);
            return new KeyPair(cert.getPublicKey(), key);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания пары ключей: ", e);
        }
    }

    public Key getKey(String keyName, String keystoreName) {
        try {
            char[] password = "changeit".toCharArray();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(keystoreName), password);
            return keyStore.getKey(keyName, password);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения пары ключей: ", e);
        }
    }
}
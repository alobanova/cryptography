package org.example.common.service.impl;

import org.example.common.service.SecretKeyService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Objects;
import java.util.Properties;

public class SecretKeyServiceImpl implements SecretKeyService {

    public static final String KEYSTORE_TYPE = "keystore-type";
    public static final String KEYSTORE_PASSWORD = "keystore-password";
    public static final String KEYSTORE_LOCATION = "keystore-location";
    public static final String SECRET_KEY_ALIAS = "secret-key-alias";

    @Override
    public Key getSecretKey() {
        try {
            Properties properties = getProperties();
            KeyStore ks = KeyStore.getInstance(properties.getProperty(KEYSTORE_TYPE));
            char[] password = properties.getProperty(KEYSTORE_PASSWORD).toCharArray();
            try (FileInputStream fis = new FileInputStream(properties.getProperty(KEYSTORE_LOCATION))) {
                ks.load(fis, password);
                return ks.getKey(properties.getProperty(SECRET_KEY_ALIAS), password);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to get secret key", e);
        }
    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try(InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("config.properties");) {
            properties.load(inputStream);
        }
        return properties;
    }
}

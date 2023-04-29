package org.example.service;

import java.io.IOException;

public interface ClientSocketService {
    void sendMessage(byte[] data) throws IOException;
}

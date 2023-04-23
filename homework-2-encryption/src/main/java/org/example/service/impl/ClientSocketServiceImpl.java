package org.example.service.impl;

import org.example.service.ClientSocketService;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocketServiceImpl implements ClientSocketService, Closeable {
    private final Socket clientSocket;
    private final DataOutputStream output;

    public ClientSocketServiceImpl() throws IOException {
        clientSocket = new Socket("localhost", 4004);
        output = new DataOutputStream(clientSocket.getOutputStream());

    }

    @Override
    public void sendMessage(byte[] data) throws IOException {
        output.writeInt(data.length);
        output.write(data);
    }

    @Override
    public void close() throws IOException {
        output.close();
        clientSocket.close();
    }
}

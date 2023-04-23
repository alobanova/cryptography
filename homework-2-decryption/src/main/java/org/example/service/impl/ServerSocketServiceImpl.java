package org.example.service.impl;

import org.example.service.ServerSocketService;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketServiceImpl implements ServerSocketService, Closeable {
    private final ServerSocket server;
    private final Socket clientSocket;
    private final DataInputStream input;

    public ServerSocketServiceImpl() throws IOException {
        server = new ServerSocket(4004);
        clientSocket = server.accept();
        input = new DataInputStream(clientSocket.getInputStream());
    }

    @Override
    public byte[] getMessage() {
        try {
            int length = input.readInt();
            if(length>0) {
                byte[] message = new byte[length];
                input.readFully(message, 0, message.length);
                return message;
            } else return new byte[0];
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public void close() throws IOException {
        input.close();
        clientSocket.close();
        server.close();
    }
}

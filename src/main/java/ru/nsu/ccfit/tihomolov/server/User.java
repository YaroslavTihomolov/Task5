package ru.nsu.ccfit.tihomolov.server;

import lombok.Getter;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class User implements Runnable, Closeable {
    private static final String host = "localhost";

    private Socket socket;

    BufferedReader reader;

    PrintWriter writer;

    @Getter
    private final UUID id;

    public User() {
        id = UUID.randomUUID();
    }

    public void connectToServer() {
        try {
            socket = new Socket(host, 8080);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }

    public void registration(final String name) {
        writer.println(name);
    }

    @Override
    public void run() {
    }
}

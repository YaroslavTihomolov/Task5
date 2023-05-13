package ru.nsu.ccfit.tihomolov.server;

import lombok.extern.log4j.Log4j2;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class KernelServer {
    private final Map<String, ServerCommunication> servers = new HashMap<>();

    private final ServerSocket serverSocket;

    public KernelServer() throws IOException {
        log.info("create kernel server");
        serverSocket = new ServerSocket(8080);
    }

    public void start() {
        Socket clientSocket;
        log.info("start server work");
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                Thread thread = new Thread(new ServerCommunication(this, clientSocket));
                thread.start();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean containsName(String name) {
        synchronized (servers) {
            return servers.containsKey(name);
        }
    }

    public void addServer(ServerCommunication serverCommunication, String name) {
        synchronized (servers) {
            servers.put(name, serverCommunication);
        }
    }
}

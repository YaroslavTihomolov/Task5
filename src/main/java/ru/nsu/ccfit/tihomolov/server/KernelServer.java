package ru.nsu.ccfit.tihomolov.server;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Log4j2
public class KernelServer {
    private final Map<String, Server> servers = new HashMap<>();
    private final List<Message<String>> messages = new LinkedList<>();

    private final ServerSocket serverSocket;

    public KernelServer() throws IOException {
        log.info("create kernel server");
        serverSocket = new ServerSocket(8080);
    }

    private void broadCastListUsers() {
        log.info("Broad cast list users");

        final Message<List<String>> message = new Message<>("MembersUpdate", servers.keySet().stream().toList());

        for (Map.Entry<String, Server> entry : servers.entrySet()) {
            entry.getValue().requestSendMessage(message);
        }
    }

    private void broadCastListMessages(List<Message<String>> messagesList) {
        log.info("Broad cast list messages");

        final Message<List<Message<String>>> message = new Message<>("Message", messagesList);

        for (Map.Entry<String, Server> entry : servers.entrySet()) {
            entry.getValue().requestSendMessage(message);
        }
    }

    private void broadCastMessagesHistory(Server server) {
        server.requestSendMessage(new Message<>("Message", messages));
    }

    public void start() {
        Socket clientSocket;
        log.info("start server work");
        while (!serverSocket.isClosed()) {
            try {
                clientSocket = serverSocket.accept();
                log.info("add user: " + clientSocket.getPort());
                Thread thread = new Thread(new Server(this, clientSocket));
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

    public void addServer(String name, Server serverCommunication) {
        synchronized (servers) {
            servers.put(name, serverCommunication);
            broadCastListUsers();
            broadCastMessagesHistory(serverCommunication);
        }
    }

    public void addMessage(String name, String userMessage) {
        synchronized (messages) {
            Message<String> message = new Message<>(name, userMessage);
            messages.add(message);
            List<Message<String>> messageList = new LinkedList<>();
            messageList.add(message);
            broadCastListMessages(messageList);
        }
    }
}

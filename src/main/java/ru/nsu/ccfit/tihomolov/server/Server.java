package ru.nsu.ccfit.tihomolov.server;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.tihomolov.univeral_classes.CommandLoader;
import ru.nsu.ccfit.tihomolov.server.command.CommandServerExecutor;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

import java.io.*;
import java.net.Socket;

@Log4j2
public class Server implements Runnable, AutoCloseable {
    private final Socket clientSocket;
    private final ObjectInputStream reader;
    CommandLoader commandLoader = new CommandLoader("ServerCommands.properties");
    @Setter
    @Getter
    String userName;
    @Getter
    private final ObjectOutputStream writer;
    @Getter
    private final KernelServer kernelServer;

    @SneakyThrows
    public Server(final KernelServer kernelServer, final Socket clientSocket) throws IOException {
        this.kernelServer = kernelServer;
        this.clientSocket = clientSocket;
        this.reader = new ObjectInputStream(this.clientSocket.getInputStream());
        this.writer = new ObjectOutputStream(this.clientSocket.getOutputStream());
    }

    public void requestSendMessage(final Message<?> message) {
        try {
            writer.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message<?> message = (Message<?>) reader.readObject();
                log.info("Get message");
                CommandServerExecutor commandServerExecutor = commandLoader.createInstanceClass(message.getTypeMessage() + "ServerExecutor");
                commandServerExecutor.execute(this, message);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        try {
            clientSocket.close();
            writer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

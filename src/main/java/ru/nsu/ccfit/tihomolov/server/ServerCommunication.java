package ru.nsu.ccfit.tihomolov.server;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.tihomolov.server.exceptions.NameContainsException;
import ru.nsu.ccfit.tihomolov.server.exceptions.NameFormatException;
import ru.nsu.ccfit.tihomolov.server.exceptions.NameLengthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Log4j2
public class ServerCommunication implements Runnable {
    public static final int NAME_MAX_LENGTH = 30;
    private final Socket clientSocket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final KernelServer kernelServer;

    @SneakyThrows
    public ServerCommunication(final KernelServer kernelServer, final Socket clientSocket) {
        this.kernelServer = kernelServer;
        this.clientSocket = clientSocket;
        this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }

    @Contract(pure = true)
    private boolean isCorrectNameFormat(final @NotNull String nameUser) throws NameFormatException {
        String regex = "^\\w+$";
        if (nameUser.matches(regex)) return true;
        throw new NameFormatException("Wrong name format, name contains letters(a-z | A-z), numbers(0-9), and symbol(_)");
    }

    @Contract(pure = true)
    private boolean isLengthCorrect(final @NotNull String name) throws NameLengthException {
        if (name.length() < NAME_MAX_LENGTH) return true;
        throw new NameLengthException("Name is too long");
    }

    private boolean containCheck(final @NotNull String name) throws NameContainsException {
        if (!kernelServer.containsName(name)) return false;
        throw new NameContainsException("Name already contain");
    }

    private boolean isNameCorrect(final String name) {
        try {
            return isCorrectNameFormat(name) && !containCheck(name) && isLengthCorrect(name) ;
        } catch (NameFormatException | NameContainsException | NameLengthException e) {
            writer.println(e.getMessage());
            log.error("Client type wrong name: " + e.getMessage());
            return false;
        }
    }

    private boolean requestAddServer() throws IOException {
        log.info("try to registration client");
        String name = null;
        do {
            name = reader.readLine();
        } while (!isNameCorrect(name));
        log.info("add client with name: " + name);
        kernelServer.addServer(this, name);
        return true;
    }

    @Override
    public void run() {
        log.info("Request to registration client");
        try {
            requestAddServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String word = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            clientSocket.close();
            writer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

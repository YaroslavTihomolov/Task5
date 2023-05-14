package ru.nsu.ccfit.tihomolov.client.controller;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.tihomolov.client.commands.CommandClientExecutor;
import ru.nsu.ccfit.tihomolov.univeral_classes.CommandLoader;
import ru.nsu.ccfit.tihomolov.client.model.User;
import ru.nsu.ccfit.tihomolov.client.view.View;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

import java.io.IOException;

@Log4j2
public class Controller {
    @Getter
    private final View view;
    @Getter
    private final User user;
    CommandLoader commandLoader;

    @SneakyThrows
    public Controller(View view) {
        this.view = view;
        commandLoader = new CommandLoader("ClientCommands.properties");
        view.setController(this);
        view.generateLogIn();
        user = new User(this);
        user.connectToServer();
    }

    public void logInTry(final String name) {
        this.user.setUserName(name);
        Message serverMessage;
        try {
            serverMessage = user.registration(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("server answer " + serverMessage);
        handleMessage(serverMessage);
    }

    public synchronized void handleMessage(Message<?> message) {
        log.info("message type: " + message.getTypeMessage());
        CommandClientExecutor commandExecutor = commandLoader.createInstanceClass(message.getTypeMessage() + "ClientExecutor");
        commandExecutor.execute(this, message);
    }

    public void sendMessage(final String userMessage) {
        user.sendMessageOnServer(userMessage);
    }

    public void printMessage(final String userMessage) {

    }
}

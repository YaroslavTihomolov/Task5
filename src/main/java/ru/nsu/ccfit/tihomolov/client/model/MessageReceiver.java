package ru.nsu.ccfit.tihomolov.client.model;

import lombok.Setter;
import ru.nsu.ccfit.tihomolov.client.controller.Controller;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageReceiver implements Runnable {
    @Setter
    Controller controller;
    @Setter
    ObjectInputStream reader;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                controller.handleMessage((Message<?>) reader.readObject());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

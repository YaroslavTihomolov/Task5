package ru.nsu.ccfit.tihomolov.client.controller;

import lombok.SneakyThrows;
import ru.nsu.ccfit.tihomolov.server.User;
import ru.nsu.ccfit.tihomolov.client.view.View;

public class Controller {
    private View view;
    private final User user;

    @SneakyThrows
    public Controller(View view) {
        this.view = view;
        view.setController(this);
        view.generateLogIn();
        user = new User();
        user.connectToServer();
    }

    public void logInTry(final String name) {
        user.registration(name);
    }
}

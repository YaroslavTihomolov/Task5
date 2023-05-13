package ru.nsu.ccfit.tihomolov.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.nsu.ccfit.tihomolov.client.controller.Controller;

public class LogInController {
    static Controller controller;

    @FXML
    private TextField name;

    public static void registrationController(Controller impController) {
        controller = impController;
    }

    public void logInButton() {
        controller.logInTry(name.getText());
    }
}

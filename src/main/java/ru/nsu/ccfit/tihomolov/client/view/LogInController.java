package ru.nsu.ccfit.tihomolov.client.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.tihomolov.client.controller.Controller;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

import java.util.List;

@Log4j2
public class LogInController {
    static Controller controller;

    @FXML
    private TextField name;

    @FXML
    private TextField textField;

    @FXML
    private ListView<String> membersListView;

    @FXML
    private ListView<String> messagesListView;

    @FXML
    private Text membersCount;

    public void updateMembers(final List<String> membersList) {
        log.info("update members");
        Platform.runLater(() -> {
            membersCount.setText(String.valueOf((long) membersList.size()));
            membersListView.getItems().setAll(membersList);
            membersListView.refresh();
        });
    }

    public void updateMessages(final List<Message<String>> messages) {
        log.info("update messages");
        Platform.runLater(() -> {
            for (var message : messages) {
                messagesListView.getItems().add(message.getTypeMessage() + ":\n" + message.getContent());
            }
            messagesListView.refresh();
        });
    }

    public void handleEnterKeyPressed() {
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && (textField.getText().length() != 0)) {
                    controller.sendMessage(textField.getText());
                    textField.clear();
            }
        });
    }

    public static void registrationController(Controller impController) {
        controller = impController;
    }

    public void logInButton() {
        controller.logInTry(name.getText());
    }

    public void logInKeyPressed() {
        name.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && (name.getText().length() != 0)) {
                controller.logInTry(name.getText());
                name.clear();
            }
        });
    }
}

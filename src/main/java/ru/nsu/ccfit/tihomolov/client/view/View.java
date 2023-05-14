package ru.nsu.ccfit.tihomolov.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import ru.nsu.ccfit.tihomolov.client.controller.Controller;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class View {
    Stage stage;
    Pane root;
    Text error = new Text(100, 460, "");
    LogInController logInController;

    @Setter
    Controller controller;

    public View(final Stage stage) {
        this.stage = stage;
        Font font = Font.font("System", 15);
        error.setFont(font);
        error.setStyle("-fx-fill: red;");
    }

    public void generateLogIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("C:\\4 семестр\\ООП\\Task5\\src\\main\\resources\\client\\LogIn.fxml").toURI().toURL());
        root = fxmlLoader.load();
        root.getChildren().add(error);

        Scene scene = new Scene(root);
        stage.setTitle("Chat");
        stage.getIcons().add(new Image("C:\\4 семестр\\ООП\\Task5\\src\\main\\resources\\client\\chat.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void printErrorMessage(String error) {
        this.error.setText(error);
    }

    public void updateMembers(final List<String> membersList) { logInController.updateMembers(membersList); }

    public void printMessages(final Message<List<Message<String>>> messages) {
        logInController.updateMessages(messages.getContent());
    }

    public void swapToChat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("C:\\4 семестр\\ООП\\Task5\\src\\main\\resources\\client\\chat.fxml").toURI().toURL());
        root = fxmlLoader.load();
        logInController = fxmlLoader.getController();
        stage.setScene(new Scene(root));
    }
}

package ru.nsu.ccfit.tihomolov.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Setter;
import ru.nsu.ccfit.tihomolov.client.controller.Controller;

import java.io.File;
import java.io.IOException;

public class View {
    Stage stage;

    @Setter
    Controller controller;

    public View(final Stage stage) {
        this.stage = stage;
    }

    public void generateLogIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("C:\\4 семестр\\ООП\\Task5\\src\\main\\resources\\chat.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Chat");
        stage.getIcons().add(new Image("chat.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

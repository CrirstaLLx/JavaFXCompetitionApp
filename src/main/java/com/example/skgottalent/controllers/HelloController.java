package com.example.skgottalent.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try {
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/skgottalent/main-view.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Slovakia Got Talent!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCloseButtonClick() {
        Platform.exit();
    }
}
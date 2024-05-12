/**
 * The HelloController class controls the behavior of the "Hello" view in the Slovakia Got Talent application.
 * It handles actions such as button clicks to navigate to the main view or close the application.
 */
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

    /** The label displaying the welcome text. */
    @FXML
    private Label welcomeText;

    /**
     * Handles the action when the "Hello" button is clicked.
     * Loads the main view and sets it as the scene for the stage.
     */
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

    /**
     * Handles the action when the "Close" button is clicked.
     * Exits the application.
     */
    @FXML
    protected void onCloseButtonClick() {
        Platform.exit();
    }
}

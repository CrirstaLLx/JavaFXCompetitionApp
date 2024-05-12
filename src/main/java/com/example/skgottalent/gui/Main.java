/**
 * The Main class is the entry point for the Slovakia Got Talent application.
 * It extends the Application class from JavaFX and implements the start method to initialize the GUI.
 */
package com.example.skgottalent.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /**
     * Initializes the GUI of the application by loading the FXML file and setting up the main stage.
     *
     * @param stage the primary stage for the application
     * @throws IOException if an error occurs while loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/skgottalent/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Slovakia Got Talent!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method, which launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}

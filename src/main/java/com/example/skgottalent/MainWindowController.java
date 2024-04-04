package com.example.skgottalent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController {
    @FXML
    private Label mainWindowLabel;

    public void initialize() {
        mainWindowLabel.setText("Test");
    }
}

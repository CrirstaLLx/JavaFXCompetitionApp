package com.example.skgottalent.controllers;

import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonViewController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label titlePersonLabel;

    @FXML
    private TextField additionalField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;


    private Stage stage;
    private String memberType;

    private MainWindowController mainWindowController;

    public void setMemberType(String memberType) {
        this.memberType = memberType;

        if ("Participant".equals(memberType)) {
            titleLabel.setText("Add Participant");
            titlePersonLabel.setText("Talent");
        } else if ("Judge".equals(memberType)) {
            titleLabel.setText("Add Judge");
            titlePersonLabel.setText("Specialization");
        }
    }

    @FXML
    private void save() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
//        int age = Integer.parseInt(ageField.getText());
        String additionalInfo = additionalField.getText().trim();

        if (name.isEmpty() || ageText.isEmpty() || additionalInfo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid age format. Please enter a valid number.");
            alert.showAndWait();
            return;
        }

        if ("Participant".equals(memberType)) {
            Participant participant = new Participant(name, age, additionalInfo);
            mainWindowController.addParticipant(participant);
        } else if ("Judge".equals(memberType)) {
            Judge judge = new Judge(name, age, additionalInfo);
            mainWindowController.addJudge(judge);
        }

        close();
    }

    @FXML
    private void close() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}

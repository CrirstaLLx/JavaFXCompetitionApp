/**
 * The PersonViewController class controls the behavior of the person view in the Slovakia Got Talent application.
 * It handles the addition of participants and judges, validating input fields, and communicating with the main window controller.
 */
package com.example.skgottalent.controllers;

import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonViewController {

    /** The label displaying the title of the view. */
    @FXML
    private Label titleLabel;

    /** The label displaying the title for the person type. */
    @FXML
    private Label titlePersonLabel;

    /** The text field for additional information (talent type or specialization). */
    @FXML
    private TextField additionalField;

    /** The text field for the name. */
    @FXML
    private TextField nameField;

    /** The text field for the age. */
    @FXML
    private TextField ageField;

    /** The stage for the view. */
    private Stage stage;

    /** The type of member (participant or judge). */
    private String memberType;

    /** The reference to the main window controller. */
    private MainWindowController mainWindowController;

    /**
     * Sets the member type and updates the UI accordingly.
     *
     * @param memberType the type of member (participant or judge)
     */
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

    /**
     * Saves the input data for the participant or judge.
     * Validates input fields before saving.
     */
    @FXML
    private void save() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
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

    /**
     * Closes the view.
     */
    @FXML
    private void close() {
        stage.close();
    }

    /**
     * Sets the stage for the view.
     *
     * @param stage the stage for the view
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the reference to the main window controller.
     *
     * @param mainWindowController the main window controller
     */
    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}

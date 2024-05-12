package com.example.skgottalent.controllers;

import com.example.skgottalent.competition.CompetitionStage;
import com.example.skgottalent.exceptions.DataLoadException;
import com.example.skgottalent.exceptions.DataSaveException;
import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the main window of the application.
 */
public class MainWindowController implements Initializable {
    // Add start list of judges and participants
    @FXML
    private ListView<Participant> participantListView;

    @FXML
    private ListView<Judge> judgeListView;

    private ObservableList<Participant> participantsList;
    private ObservableList<Judge> judgesList;

    @FXML
    private Label mainWindowLabel;

    /**
     * Method to handle the event when the "Add Judge" button is clicked.
     */
    @FXML
    private void onAddJudgeClick() {
        openMemberView("Judge");
    }

    /**
     * Method to handle the event when the "Add Participant" button is clicked.
     */
    @FXML
    private void onAddParticipantClick() {
        openMemberView("Participant");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        participantsList = FXCollections.observableArrayList();
        judgesList = FXCollections.observableArrayList();

        participantListView.setItems(participantsList);
        judgeListView.setItems(judgesList);
    }

    public void addParticipant(Participant participant) {
        participantsList.add(participant);
    }

    public void addJudge(Judge judge) {
        judgesList.add(judge);
    }

    private void openMemberView(String memberType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skgottalent/person-view.fxml"));
            Parent root = loader.load();

            PersonViewController controller = loader.getController();
            controller.setMemberType(memberType);
            controller.setMainWindowController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle the event when the "Start Game" button is clicked.
     */
    @FXML
    protected void onStartGameClick() {
        if (participantsList.isEmpty() || judgesList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Participant or judge list is empty. Please add participants and judges before starting the game.");
            alert.showAndWait();
        } else {
            try {
                Stage stage = (Stage) mainWindowLabel.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/skgottalent/competition-view.fxml"));
                Parent root = fxmlLoader.load();
                //send to CompetitionController lists of participation and judge
                CompetitionController competitionController = fxmlLoader.getController();
                competitionController.setJudgesList(judgesList);
                CompetitionStage currentStage = CompetitionStage.CASTING;
                competitionController.setParticipantsList(participantsList, currentStage);
                stage.setScene(new Scene(root));
                stage.setTitle("Slovakia Got Talent!");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveLists(String fileName) throws DataSaveException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(new ArrayList<>(participantsList));
            oos.writeObject(new ArrayList<>(judgesList));
        } catch (IOException e) {
            throw new DataSaveException("Failed to save data to file: " + fileName, e);
        }
    }

    public void loadLists(String fileName) throws DataLoadException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            participantsList = FXCollections.observableArrayList((List<Participant>) ois.readObject());
            judgesList = FXCollections.observableArrayList((List<Judge>) ois.readObject());
            participantListView.setItems(participantsList);
            judgeListView.setItems(judgesList);
        } catch (IOException | ClassNotFoundException e) {
            throw new DataLoadException("Failed to load data from file: " + fileName, e);
        }
    }

    /**
     * Method to handle the event when the "Save" button is clicked.
     */
    @FXML
    private void onSaveClick() {
        try {
            saveLists("lists.dat");
        } catch (DataSaveException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Error");
            alert.setHeaderText("Error saving data");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Method to handle the event when the "Load" button is clicked.
     */
    @FXML
    private void onLoadClick() {
        try {
            loadLists("lists.dat");
        } catch (DataLoadException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Error");
            alert.setHeaderText("Error loading data");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

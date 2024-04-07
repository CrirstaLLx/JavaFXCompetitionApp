package com.example.skgottalent.controllers;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private ListView<Participant> participantListView;

    @FXML
    private ListView<Judge> judgeListView;

    private ObservableList<Participant> participantsList;
    private ObservableList<Judge> judgesList;

    @FXML
    private Label mainWindowLabel;

    @FXML
    private void onAddJudgeClick() {
        openMemberView("Judge");
    }

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
                stage.setScene(new Scene(root));
                stage.setTitle("Slovakia Got Talent!");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

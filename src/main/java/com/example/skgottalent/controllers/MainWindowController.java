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
}

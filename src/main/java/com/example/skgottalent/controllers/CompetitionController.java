package com.example.skgottalent.controllers;

import com.example.skgottalent.competition.Competition;
import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class CompetitionController {

    @FXML
    private Label competitionLabel;

    @FXML
    private TableView<Participant> participantTableView;

    @FXML
    private TableColumn<Participant, String> nameColumn;

    @FXML
    private TableColumn<Participant, Integer> ageColumn;

    @FXML
    private TableColumn<Participant, String> talentTypeColumn;

    @FXML
    private TableColumn<Participant, Double> scoreColumn;

    private Competition competition;

    public void initialize() {
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        ageColumn.setCellValueFactory(data -> data.getValue().ageProperty().asObject());
        talentTypeColumn.setCellValueFactory(data -> data.getValue().talentTypeProperty());
        scoreColumn.setCellValueFactory(data -> data.getValue().averageScoreProperty().asObject());
        competition = new Competition();
        competition.startCasting();
        updateInterface();
    }

    private void updateInterface() {
        competitionLabel.setText("Current Stage: " + competition.getCurrentStage());
        participantTableView.getItems().clear();
        participantTableView.getItems().addAll(competition.getParticipants());
    }
}

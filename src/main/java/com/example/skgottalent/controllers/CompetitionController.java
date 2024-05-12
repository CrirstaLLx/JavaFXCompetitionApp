/**
 * The CompetitionController class controls the behavior of the competition view in the Slovakia Got Talent application.
 * It manages the competition stages, displays participants, and handles actions such as continuing to the next stage.
 */
package com.example.skgottalent.controllers;

import com.example.skgottalent.competition.Competition;
import com.example.skgottalent.competition.CompetitionStage;
import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompetitionController {

    /** The competition instance managing the competition stages. */
    private Competition competition;

    /** The TableView displaying the list of participants. */
    @FXML
    private TableView<Participant> participantTableView;

    /** The TableColumn for participant names. */
    @FXML
    private TableColumn<Participant, String> nameColumn;

    /** The TableColumn for participant ages. */
    @FXML
    private TableColumn<Participant, Integer> ageColumn;

    /** The TableColumn for participant talent types. */
    @FXML
    private TableColumn<Participant, String> talentTypeColumn;

    /** The TableColumn for participant average scores. */
    @FXML
    private TableColumn<Participant, Double> scoreColumn;

    /** The TextArea for displaying competition messages. */
    @FXML
    private TextArea textArea;

    /** The ListView for displaying participants in the next round. */
    @FXML
    private ListView<Participant> nextRoundListView;

    /** The Label for displaying the current competition stage. */
    @FXML
    private Label label;

    /** The list of judges. */
    private ObservableList<Judge> judgesList;

    /**
     * Initializes the TableView columns.
     */
    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        talentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("talentType"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("averageScore"));
    }

    /**
     * Sets the list of participants for the current competition stage and starts the competition.
     *
     * @param participantsList the list of participants
     * @param currentStage the current stage of the competition
     */
    public void setParticipantsList(ObservableList<Participant> participantsList, CompetitionStage currentStage) {
        participantTableView.setItems(participantsList);
        competition = new Competition(judgesList, this, currentStage);
        for (Participant participant : participantsList) {
            competition.addParticipant(participant);
        }
        competition.startStage(textArea, participantTableView);
    }

    /**
     * Sets the list of judges.
     *
     * @param judgesList the list of judges
     */
    public void setJudgesList(ObservableList<Judge> judgesList) {
        this.judgesList = judgesList;
    }

    /**
     * Sets the list of participants for the next round.
     *
     * @param nextRoundParticipantsList the list of participants for the next round
     */
    public void setNextRoundParticipantsList(ObservableList<Participant> nextRoundParticipantsList) {
        nextRoundListView.setItems(nextRoundParticipantsList);
    }

    /**
     * Resets the scores of all participants.
     */
    public void resetScores() {
        ObservableList<Participant> participants = participantTableView.getItems();
        for (Participant participant : participants) {
            participant.resetScore();
        }
    }

    /**
     * Updates the label displaying the current competition stage.
     *
     * @param currentStage the current stage of the competition
     */
    public void updateCompetitionLabel(CompetitionStage currentStage) {
        switch (currentStage) {
            case CASTING:
                label.setText("Casting");
                break;
            case SEMI_FINAL:
                label.setText("Semi-final");
                break;
            case FINAL:
                label.setText("Final");
                break;
            default:
                break;
        }
    }

    /**
     * Handles the action when the "Continue" button is clicked to proceed to the next stage of the competition.
     */
    @FXML
    private void continueNextStage() {
        resetScores();
        competition.startNextStage(textArea, participantTableView, nextRoundListView);
    }
}

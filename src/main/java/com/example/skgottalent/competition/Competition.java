/**
 * The Competition class represents a competition in the Slovakia Got Talent application.
 * It manages the stages of the competition, calculates scores, determines winners, and displays competition progress.
 */
package com.example.skgottalent.competition;

import com.example.skgottalent.controllers.CompetitionController;
import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

public class Competition {

    /** The list of participants in the competition. */
    private List<Participant> participants;

    /** The list of judges in the competition. */
    private List<Judge> judges;

    /** The list of participants qualified for the next round. */
    private List<Participant> nextRoundParticipants;

    /** The current stage of the competition. */
    private CompetitionStage currentStage;

    /** The controller managing the competition view. */
    private CompetitionController competitionController;

    /** The random number generator for generating scores. */
    private Random random;

    /**
     * Constructs a Competition object.
     *
     * @param judges the list of judges
     * @param competitionController the controller managing the competition view
     * @param currentStage the current stage of the competition
     */
    public Competition(List<Judge> judges, CompetitionController competitionController, CompetitionStage currentStage) {
        this.competitionController = competitionController;
        this.judges = judges;
        this.participants = new ArrayList<>();
        this.nextRoundParticipants = new ArrayList<>();
        this.currentStage = currentStage;
        this.random = new Random();
    }

    /**
     * Adds a participant to the competition.
     *
     * @param participant the participant to add
     */
    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    /**
     * Starts the current stage of the competition.
     *
     * @param textArea the text area for displaying competition messages
     * @param participantTableView the table view displaying participants
     */
    public void startStage(TextArea textArea, TableView<Participant> participantTableView) {
        updateLabel(currentStage);
        textArea.clear();

        new Thread(() -> {
            participants.forEach(participant -> {
                StringBuilder feedback = new StringBuilder(String.format("Participant: %s\n", participant.getName()));
                double totalScore = judges.stream()
                        .mapToInt(judge -> {
                            int score = randomScore();
                            feedback.append(String.format("Judge: %s Score: %d\n", judge.getName(), score));
                            try {
                                Thread.sleep(200); // Delay to simulate sequential output
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Platform.runLater(() -> textArea.appendText(feedback.toString() + "\n"));
                            feedback.setLength(0); // Clear StringBuilder for the next judge
                            return score;
                        })
                        .sum();
                double averageScore = totalScore / judges.size();
                participant.setAverageScore(averageScore);
                feedback.append(String.format("Average score: %.2f\n\n", averageScore));

                // Update participant information in the UI after calculating all scores
                Platform.runLater(() -> {
                    participantTableView.refresh();
                    textArea.appendText(feedback.toString());
                });
            });

            // Proceed to the next stage or determine the winner after all calculations are complete
            if (currentStage != CompetitionStage.FINAL) {
                double minThreshold = participants.stream()
                        .mapToDouble(Participant::getAverageScore)
                        .average()
                        .orElse(0.0);

                nextRoundParticipants = participants.stream()
                        .filter(p -> p.getAverageScore() > minThreshold)
                        .collect(Collectors.toList());

                Platform.runLater(() -> {
                    competitionController.setNextRoundParticipantsList(FXCollections.observableArrayList(nextRoundParticipants));
                    competitionController.updateCompetitionLabel(currentStage);
                });
            } else {
                Participant winner = findWinner();
                Platform.runLater(() -> {
                    textArea.appendText("Winner: " + winner.getName() + " with score: " + winner.getAverageScore() + "\n");
                    showAlertWinner(winner);
                    competitionController.updateCompetitionLabel(currentStage);
                });
            }
            advanceStage();
        }).start();
    }

    /**
     * Generates a random score between 1 and 5.
     *
     * @return the random score
     */
    private int randomScore() {
        return random.nextInt(5) + 1;
    }

    /**
     * Displays a winner alert.
     *
     * @param winner the winner participant
     */
    private void showAlertWinner(Participant winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations to the winner: " + winner.getName());
        alert.showAndWait();
        Platform.exit();
    }

    /**
     * Finds the participant with the highest average score (winner).
     *
     * @return the winner participant
     */
    private Participant findWinner() {
        return participants.stream()
                .max(Comparator.comparingDouble(Participant::getAverageScore))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Advances the competition to the next stage.
     */
    private void advanceStage() {
        switch (currentStage) {
            case CASTING:
                currentStage = CompetitionStage.SEMI_FINAL;
                break;
            case SEMI_FINAL:
                currentStage = CompetitionStage.FINAL;
                break;
            default:
                // Do nothing if already in the final stage
                break;
        }
    }

    /**
     * Updates the label displaying the current competition stage.
     *
     * @param stage the current stage of the competition
     */
    private void updateLabel(CompetitionStage stage) {
        competitionController.updateCompetitionLabel(stage);
    }

    /**
     * Starts the next stage of the competition.
     *
     * @param textArea the text area for displaying competition messages
     * @param participantTableView the table view displaying participants
     * @param nextRoundListView the list view displaying participants qualified for the next round
     */
    public void startNextStage(TextArea textArea, TableView<Participant> participantTableView, ListView<Participant> nextRoundListView) {
        textArea.clear();
        nextRoundParticipants.forEach(Participant::resetScore);

        participantTableView.getItems().clear();
        nextRoundListView.getItems().clear();

        participantTableView.setItems(FXCollections.observableArrayList(nextRoundParticipants));
        nextRoundListView.setItems(FXCollections.observableArrayList(nextRoundParticipants));

        competitionController.setParticipantsList(FXCollections.observableArrayList(nextRoundParticipants), currentStage);
    }
}

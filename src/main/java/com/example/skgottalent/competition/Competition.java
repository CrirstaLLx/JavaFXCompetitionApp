package com.example.skgottalent.competition;

import com.example.skgottalent.models.Judge;
import com.example.skgottalent.models.Participant;

import java.util.ArrayList;
import java.util.List;

public class Competition {
    private List<Participant> participants;
    private List<Judge> judges;
    private CompetitionStage currentStage;

    public Competition(List<Judge> judges) {
        this.judges = judges;
        participants = new ArrayList<>();
        currentStage = CompetitionStage.CASTING;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void startCasting() {
        if (currentStage != CompetitionStage.CASTING) {
            System.out.println("The competition has already progressed beyond the casting stage.");
            return;
        }

        for (Participant participant : participants) {
            double totalScore = 0;
            for (Judge judge : judges) {
                int score = (int) (Math.random() * 5) + 1;
                totalScore += score;
            }
            double averageScore = totalScore / judges.size();
            participant.setAverageScore(averageScore);
        }

        double totalScore = 0;
        for (Participant participant : participants) {
            totalScore += participant.getAverageScore();
        }
        double minThreshold = totalScore / participants.size();

        System.out.println("Minimum threshold for next stage: " + minThreshold);

        currentStage = CompetitionStage.SEMI_FINAL;
    }
}

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
}

package com.example.skgottalent.competition;

import com.example.skgottalent.models.Participant;

public class ParticipantScore extends Participant {
    private int score;

    public ParticipantScore(String name, int age, String talentType, int score) {
        super(name, age, talentType);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

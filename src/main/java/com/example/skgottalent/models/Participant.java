/**
 * The Participant class represents a participant in a talent competition, extending the Person class.
 * This class implements the Serializable interface to support serialization.
 */
package com.example.skgottalent.models;

import java.io.Serializable;

public class Participant extends Person implements Serializable {

    /** The type of talent the participant possesses. */
    private String talentType;

    /** The average score of the participant. */
    private double averageScore;

    /**
     * Constructs a new Participant with the specified name, age, and talent type.
     *
     * @param name the name of the participant
     * @param age the age of the participant
     * @param talentType the type of talent the participant possesses
     */
    public Participant(String name, int age, String talentType) {
        super(name, age);
        this.talentType = talentType;
    }

    /**
     * Constructs a new Participant with default values for name, age, and talent type.
     */
    public Participant() {}

    /**
     * Gets the type of talent the participant possesses.
     *
     * @return the type of talent the participant possesses
     */
    public String getTalentType() {
        return talentType;
    }

    /**
     * Sets the type of talent the participant possesses.
     *
     * @param talentType the new type of talent the participant possesses
     */
    public void setTalentType(String talentType) {
        this.talentType = talentType;
    }

    /**
     * Gets the average score of the participant.
     *
     * @return the average score of the participant
     */
    public double getAverageScore() {
        return averageScore;
    }

    /**
     * Sets the average score of the participant.
     *
     * @param averageScore the new average score of the participant
     */
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Resets the average score of the participant to zero.
     */
    public void resetScore() {
        this.averageScore = 0;
    }

    /**
     * Returns a string representation of the participant, including name, age, and talent type.
     *
     * @return a string representation of the participant
     */
    @Override
    public String toString() {
        return "Name: " + getName() + ", Age: " + getAge() + ", Talent type: " + getTalentType();
    }
}

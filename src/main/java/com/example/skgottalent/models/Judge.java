/**
 * The Judge class represents a judge in a talent competition, extending the Person class.
 * This class implements the Serializable interface to support serialization.
 */
package com.example.skgottalent.models;

import java.io.Serializable;

public class Judge extends Person implements Serializable {

    /** The specialization of the judge. */
    private String specialization;

    /**
     * Constructs a new Judge with the specified name, age, and specialization.
     *
     * @param name the name of the judge
     * @param age the age of the judge
     * @param specialization the specialization of the judge
     */
    public Judge(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    /**
     * Gets the specialization of the judge.
     *
     * @return the specialization of the judge
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the judge.
     *
     * @param specialization the new specialization of the judge
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Returns a string representation of the judge, including name, age, and specialization.
     *
     * @return a string representation of the judge
     */
    @Override
    public String toString() {
        return "Name: " + getName() + ", Age: " + getAge() + ", Specialization: " + getSpecialization();
    }
}

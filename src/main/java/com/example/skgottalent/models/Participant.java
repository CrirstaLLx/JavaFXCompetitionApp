package com.example.skgottalent.models;

public class Participant extends Person {
    private String talentType;

    public Participant(String name, int age, String talentType) {
        super(name, age);
        this.talentType = talentType;
    }

    public Participant() {}

    public String getTalentType() {
        return talentType;
    }

    public void setTalentType(String talentType) {
        this.talentType = talentType;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Age: " + getAge() + ", Talent type: " + getTalentType();
    }
}

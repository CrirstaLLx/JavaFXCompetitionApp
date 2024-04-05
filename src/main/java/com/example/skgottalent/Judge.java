package com.example.skgottalent;

public class Judge extends Person {
    private String specialization;

    public Judge(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Age: " + getAge() + ", Talent type: " + getSpecialization();
    }
}

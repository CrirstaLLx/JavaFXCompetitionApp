/**
 * The Person class represents a generic person with a name and an age.
 * This class implements the Serializable interface to support serialization.
 */
package com.example.skgottalent.models;

import java.io.Serializable;

public class Person implements Serializable {

    /** The name of the person. */
    protected String name;

    /** The age of the person. */
    protected int age;

    /**
     * Constructs a new Person with the specified name and age.
     *
     * @param name the name of the person
     * @param age the age of the person
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Constructs a new Person with default values for name and age.
     */
    public Person() {}

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     *
     * @param age the new age of the person
     */
    public void setAge(int age) {
        this.age = age;
    }
}

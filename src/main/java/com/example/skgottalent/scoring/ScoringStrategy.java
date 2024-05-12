package com.example.skgottalent.scoring;

import com.example.skgottalent.models.Judge;

import java.util.List;

/**
 * Interface for defining scoring strategies.
 */
public interface ScoringStrategy {
    /**
     * Calculates the score based on a list of judges' assessments.
     *
     * @param judges The list of judges providing the assessments.
     * @return The calculated score.
     */
    double calculateScore(List<Judge> judges);
}

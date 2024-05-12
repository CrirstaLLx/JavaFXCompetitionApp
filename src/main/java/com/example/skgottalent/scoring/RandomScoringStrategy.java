package com.example.skgottalent.scoring;

import com.example.skgottalent.models.Judge;

import java.util.List;
import java.util.Random;

/**
 * A scoring strategy that generates random scores for each judge.
 */
public class RandomScoringStrategy implements ScoringStrategy {
    private Random random = new Random();

    /**
     * Calculates the average score based on random scores assigned by each judge.
     *
     * @param judges The list of judges providing the scores.
     * @return The calculated average score.
     */
    @Override
    public double calculateScore(List<Judge> judges) {
        return judges.stream()
                .mapToDouble(judge -> random.nextInt(5) + 1)
                .average()
                .orElse(0);  // Returns 0 if the list of judges is empty
    }
}

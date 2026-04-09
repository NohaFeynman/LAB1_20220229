package com.example.lab1_20220229;

import java.util.ArrayList;

public class StatsManager {

    public static ArrayList<GameRecord> history = new ArrayList<>();
    public static int longestCorrectStreak = 0;
    public static int totalCorrectAnswers = 0;
    public static int totalAnsweredQuestions = 0;

    public static void addFinishedGame(String difficulty, String startTime, String duration, int hintsUsed, int score) {
        history.add(new GameRecord(difficulty, startTime, duration, hintsUsed, score, "FINISHED"));
    }

    public static void addCancelledGame(String difficulty, String startTime, String duration, int hintsUsed, int score) {
        history.add(new GameRecord(difficulty, startTime, duration, hintsUsed, score, "CANCELLED"));
    }

    public static int getPlayedGamesCount() {
        int count = 0;
        for (GameRecord record : history) {
            if (!record.getStatus().equals("IN_PROGRESS")) {
                count++;
            }
        }
        return count;
    }

    public static double getCorrectPercentage() {
        if (totalAnsweredQuestions == 0) {
            return 0.0;
        }
        return (totalCorrectAnswers * 100.0) / totalAnsweredQuestions;
    }

    public static double getAverageScore() {
        int finishedGames = 0;
        int totalScore = 0;

        for (GameRecord record : history) {
            if (record.getStatus().equals("FINISHED")) {
                finishedGames++;
                totalScore += record.getScore();
            }
        }

        if (finishedGames == 0) {
            return 0.0;
        }

        return totalScore / (double) finishedGames;
    }
}
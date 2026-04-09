package com.example.lab1_20220229;

public class GameRecord {

    private String difficulty;
    private String startTime;
    private String duration;
    private int hintsUsed;
    private int score;
    private String status; // FINISHED, CANCELLED, IN_PROGRESS

    public GameRecord(String difficulty, String startTime, String duration, int hintsUsed, int score, String status) {
        this.difficulty = difficulty;
        this.startTime = startTime;
        this.duration = duration;
        this.hintsUsed = hintsUsed;
        this.score = score;
        this.status = status;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDuration() {
        return duration;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public int getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }
}
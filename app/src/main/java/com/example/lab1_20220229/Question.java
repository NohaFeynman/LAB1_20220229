package com.example.lab1_20220229;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable {

    private String text;
    private ArrayList<String> options;
    private int correctIndex;
    private String difficulty;

    private int selectedIndex;
    private boolean answered;
    private int pointsEarned;
    private boolean correct;
    private boolean hintUsed;
    private int streakPoints;

    public Question(String text, ArrayList<String> options, int correctIndex, String difficulty) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        this.difficulty = difficulty;

        this.selectedIndex = -1;
        this.answered = false;
        this.pointsEarned = 0;
        this.correct = false;

        this.hintUsed = false;
        this.streakPoints = 0;
    }

    public void shuffleOptions() {
        String correctOption = options.get(correctIndex);
        Collections.shuffle(options);
        correctIndex = options.indexOf(correctOption);
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public void setHintUsed(boolean hintUsed) {
        this.hintUsed = hintUsed;
    }

    public int getStreakPoints() {
        return streakPoints;
    }

    public void setStreakPoints(int streakPoints) {
        this.streakPoints = streakPoints;
    }
}
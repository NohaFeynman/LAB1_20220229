package com.example.lab1_20220229;

public class GameSession {

    public static String playerName = "";
    public static String appStartTime = "";

    public static String currentDifficulty = "";
    public static String currentGameStartTimeText = "";
    public static long currentGameStartTimeMillis = 0L;

    public static int currentHintsUsed = 0;
    public static boolean gameInProgress = false;

    public static void resetCurrentGame() {
        currentDifficulty = "";
        currentGameStartTimeText = "";
        currentGameStartTimeMillis = 0L;
        currentHintsUsed = 0;
        gameInProgress = false;
    }
}
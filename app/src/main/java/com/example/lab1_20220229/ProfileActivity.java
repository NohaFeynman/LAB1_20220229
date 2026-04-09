package com.example.lab1_20220229;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvPlayerName;
    private TextView tvAppStartTime;
    private TextView tvGamesCount;
    private TextView tvLongestStreak;
    private TextView tvCorrectPercentage;
    private TextView tvAverageScore;
    private LinearLayout layoutHistory;

    private String playerName;
    private boolean fromQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvPlayerName = findViewById(R.id.tvPlayerName);
        tvAppStartTime = findViewById(R.id.tvAppStartTime);
        tvGamesCount = findViewById(R.id.tvGamesCount);
        tvLongestStreak = findViewById(R.id.tvLongestStreak);
        tvCorrectPercentage = findViewById(R.id.tvCorrectPercentage);
        tvAverageScore = findViewById(R.id.tvAverageScore);
        layoutHistory = findViewById(R.id.layoutHistory);

        playerName = getIntent().getStringExtra(AppConstants.EXTRA_PLAYER_NAME);
        fromQuiz = getIntent().getBooleanExtra(AppConstants.EXTRA_FROM_QUIZ, false);

        if (playerName == null) {
            playerName = "";
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ArtemisQuiz");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadProfileInfo();
        loadHistory();
    }

    private void loadProfileInfo() {
        tvPlayerName.setText("Jugador: " + playerName);
        tvAppStartTime.setText("Inicio: " + GameSession.appStartTime);
        tvGamesCount.setText("Cantidad de partidas: " + StatsManager.getPlayedGamesCount());
        tvLongestStreak.setText("Racha más larga: " + StatsManager.longestCorrectStreak);

        tvCorrectPercentage.setText(
                String.format(Locale.getDefault(), "Preguntas acertadas: %.2f%%", StatsManager.getCorrectPercentage())
        );

        tvAverageScore.setText(
                String.format(Locale.getDefault(), "Promedio de puntaje: %.2f", StatsManager.getAverageScore())
        );
    }

    private void loadHistory() {
        layoutHistory.removeAllViews();

        int gameNumber = 1;

        for (GameRecord record : StatsManager.history) {
            TextView label = new TextView(this);
            label.setText("Juego " + gameNumber + ":");
            label.setTextSize(16);
            label.setTextColor(Color.BLACK);
            label.setPadding(0, 0, 0, 4);
            layoutHistory.addView(label);

            TextView detail = new TextView(this);
            detail.setTextSize(16);
            detail.setPadding(30, 0, 0, 18);

            if (record.getStatus().equals("CANCELLED")) {
                detail.setText("Canceló");
                detail.setTextColor(Color.BLACK);
            } else {
                String text = record.getDifficulty()
                        + " | Tiempo: " + record.getDuration()
                        + " | Pistas: " + record.getHintsUsed()
                        + " | Puntaje: " + record.getScore();

                detail.setText(text);

                if (record.getScore() >= 0) {
                    detail.setTextColor(Color.parseColor("#2EAF2E"));
                } else {
                    detail.setTextColor(Color.parseColor("#D61F1F"));
                }
            }

            layoutHistory.addView(detail);
            gameNumber++;
        }

        if (fromQuiz && GameSession.gameInProgress) {
            TextView label = new TextView(this);
            label.setText("Juego " + gameNumber + ":");
            label.setTextSize(16);
            label.setTextColor(Color.BLACK);
            label.setPadding(0, 0, 0, 4);
            layoutHistory.addView(label);

            TextView inProgressView = new TextView(this);
            inProgressView.setText(
                    GameSession.currentDifficulty
                            + " | Inicio: "
                            + GameSession.currentGameStartTimeText
                            + " | En curso"
            );
            inProgressView.setTextColor(Color.parseColor("#F39C12"));
            inProgressView.setTextSize(16);
            inProgressView.setPadding(30, 0, 0, 18);
            layoutHistory.addView(inProgressView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
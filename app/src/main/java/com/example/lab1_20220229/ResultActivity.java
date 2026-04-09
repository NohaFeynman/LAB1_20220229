package com.example.lab1_20220229;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private String playerName;
    private String difficulty;
    private int finalScore;

    private TextView tvResultDifficulty;
    private TextView tvFinalScore;
    private Button btnBackResult;
    private Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultDifficulty = findViewById(R.id.tvResultDifficulty);
        tvFinalScore = findViewById(R.id.tvFinalScore);
        btnBackResult = findViewById(R.id.btnBackResult);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        playerName = getIntent().getStringExtra(AppConstants.EXTRA_PLAYER_NAME);
        difficulty = getIntent().getStringExtra(AppConstants.EXTRA_DIFFICULTY);
        finalScore = getIntent().getIntExtra(AppConstants.EXTRA_FINAL_SCORE, 0);

        if (playerName == null) playerName = "";
        if (difficulty == null) difficulty = "";

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ArtemisQuiz");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvResultDifficulty.setText("Dificultad: " + difficulty);
        tvFinalScore.setText(String.valueOf(finalScore));

        if (finalScore >= 0) {
            tvFinalScore.setBackgroundColor(Color.parseColor("#C8E6C9"));
        } else {
            tvFinalScore.setBackgroundColor(Color.parseColor("#FFCDD2"));
        }

        btnBackResult.setOnClickListener(v -> finish());

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(this, DifficultyActivity.class);
            intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
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
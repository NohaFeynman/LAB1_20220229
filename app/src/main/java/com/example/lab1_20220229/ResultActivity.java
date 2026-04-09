package com.example.lab1_20220229;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private String playerName;
    private String difficulty;
    private int finalScore;

    private TextView tvFinalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvFinalScore = findViewById(R.id.tvFinalScore);

        playerName = getIntent().getStringExtra(AppConstants.EXTRA_PLAYER_NAME);
        difficulty = getIntent().getStringExtra(AppConstants.EXTRA_DIFFICULTY);
        finalScore = getIntent().getIntExtra(AppConstants.EXTRA_FINAL_SCORE, 0);

        tvFinalScore.setText(String.valueOf(finalScore));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ArtemisQuiz");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void playAgain(android.view.View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
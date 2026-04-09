package com.example.lab1_20220229;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DifficultyActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        tvWelcome = findViewById(R.id.tvWelcome);

        playerName = getIntent().getStringExtra(AppConstants.EXTRA_PLAYER_NAME);
        if (playerName == null) {
            playerName = "";
        }

        tvWelcome.setText("BIENVENIDO\n" + playerName);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ArtemisQuiz");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void startEasyQuiz(android.view.View view) {
        openQuiz(AppConstants.DIFFICULTY_EASY);
    }

    public void startHardQuiz(android.view.View view) {
        openQuiz(AppConstants.DIFFICULTY_HARD);
    }

    public void startRandomQuiz(android.view.View view) {
        openQuiz(AppConstants.DIFFICULTY_RANDOM);
    }

    private void openQuiz(String difficulty) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
        intent.putExtra(AppConstants.EXTRA_DIFFICULTY, difficulty);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        if (id == R.id.menu_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
            intent.putExtra(AppConstants.EXTRA_FROM_QUIZ, false);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
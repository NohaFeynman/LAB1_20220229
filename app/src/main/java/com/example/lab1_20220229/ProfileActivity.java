package com.example.lab1_20220229;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvPlayerName;
    private String playerName;
    private boolean fromQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvPlayerName = findViewById(R.id.tvPlayerName);

        playerName = getIntent().getStringExtra(AppConstants.EXTRA_PLAYER_NAME);
        fromQuiz = getIntent().getBooleanExtra(AppConstants.EXTRA_FROM_QUIZ, false);

        if (playerName == null) {
            playerName = "";
        }

        tvPlayerName.setText("Jugador: " + playerName);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ArtemisQuiz");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Luego aquí cargarás estadísticas e historial
        // Si fromQuiz == true, podrás considerar el caso "En curso"
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
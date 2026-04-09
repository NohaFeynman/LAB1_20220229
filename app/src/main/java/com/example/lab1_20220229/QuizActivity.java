package com.example.lab1_20220229;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private String playerName;
    private String difficulty;

    private TextView tvDifficulty;
    private TextView tvQuestionCounter;
    private TextView tvScore;
    private TextView tvAnswerStatus;
    private TextView tvQuestionPoints;
    private TextView tvQuestion;
    private TextView tvHintsInfo;

    private Button btnHint;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnPrevious;
    private Button btnNext;

    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int totalScore;
    private int hintsUsedTotal;
    private int correctStreak;
    private int incorrectStreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        playerName = getIntent().getStringExtra(AppConstants.EXTRA_PLAYER_NAME);
        difficulty = getIntent().getStringExtra(AppConstants.EXTRA_DIFFICULTY);

        if (playerName == null) {
            playerName = "";
        }
        if (difficulty == null) {
            difficulty = "";
        }

        initViews();
        initActionBar();
        initGame();
        initListeners();
    }

    private void initViews() {
        tvDifficulty = findViewById(R.id.tvDifficulty);
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter);
        tvScore = findViewById(R.id.tvScore);
        tvAnswerStatus = findViewById(R.id.tvAnswerStatus);
        tvQuestionPoints = findViewById(R.id.tvQuestionPoints);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvHintsInfo = findViewById(R.id.tvHintsInfo);

        btnHint = findViewById(R.id.btnHint);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
    }

    private void initActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ArtemisQuiz");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initGame() {
        questions = QuestionBank.getQuestionsForGame(difficulty);
        currentQuestionIndex = 0;
        totalScore = 0;

        hintsUsedTotal = 0;
        correctStreak = 0;
        incorrectStreak = 0;

        updateQuestionUI();
    }

    private void initListeners() {
        btnOption1.setOnClickListener(v -> answerCurrentQuestion(0));
        btnOption2.setOnClickListener(v -> answerCurrentQuestion(1));
        btnOption3.setOnClickListener(v -> answerCurrentQuestion(2));
        btnOption4.setOnClickListener(v -> answerCurrentQuestion(3));

        btnPrevious.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                updateQuestionUI();
            }
        });

        btnNext.setOnClickListener(v -> {
            Question current = questions.get(currentQuestionIndex);

            if (!current.isAnswered()) {
                return;
            }

            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;
                updateQuestionUI();
            } else {
                goToResult(totalScore);
            }
        });

        btnHint.setOnClickListener(v -> useHint());
    }

    private void updateQuestionUI() {
        Question question = questions.get(currentQuestionIndex);

        tvDifficulty.setText("Dificultad: " + difficulty);
        tvQuestionCounter.setText("Pregunta " + (currentQuestionIndex + 1) + " de 5");
        tvScore.setText("Puntaje acumulado: " + totalScore);
        tvHintsInfo.setText("Pistas usadas: " + hintsUsedTotal + " de 3");
        tvQuestion.setText(question.getText());

        ArrayList<String> options = question.getOptions();
        btnOption1.setText(options.get(0));
        btnOption2.setText(options.get(1));
        btnOption3.setText(options.get(2));
        btnOption4.setText(options.get(3));

        resetOptionStyles();

        btnOption1.setVisibility(Button.VISIBLE);
        btnOption2.setVisibility(Button.VISIBLE);
        btnOption3.setVisibility(Button.VISIBLE);
        btnOption4.setVisibility(Button.VISIBLE);

        if (question.isHintUsed()) {
            hideTwoIncorrectOptions(question);
        }

        if (question.isAnswered()) {
            showAnsweredState(question);
        } else {
            tvAnswerStatus.setText("Estado: sin responder");
            tvQuestionPoints.setText("Puntaje en esta pregunta: 0");
        }

        btnPrevious.setEnabled(currentQuestionIndex > 0);
        btnNext.setEnabled(question.isAnswered());

        btnHint.setEnabled(!question.isAnswered() && !question.isHintUsed() && hintsUsedTotal < 3);
    }

    private void answerCurrentQuestion(int selectedIndex) {
        Question question = questions.get(currentQuestionIndex);

        if (question.isAnswered()) {
            return;
        }

        question.setSelectedIndex(selectedIndex);
        question.setAnswered(true);

        boolean isCorrect = selectedIndex == question.getCorrectIndex();
        question.setCorrect(isCorrect);

        int basePoints = calculateBasePoints(question.getDifficulty(), isCorrect);
        int streakPoints = calculateStreakPoints(isCorrect);

        question.setStreakPoints(streakPoints);

        int totalQuestionPoints = basePoints + streakPoints;
        question.setPointsEarned(totalQuestionPoints);

        totalScore += totalQuestionPoints;

        showAnsweredState(question);

        tvScore.setText("Puntaje acumulado: " + totalScore);
        btnNext.setEnabled(true);
        btnHint.setEnabled(false);
    }

    private int calculateBasePoints(String questionDifficulty, boolean isCorrect) {
        if (questionDifficulty.equals(AppConstants.DIFFICULTY_EASY)) {
            return isCorrect ? 2 : -3;
        } else {
            return isCorrect ? 4 : -6;
        }
    }

    private void showAnsweredState(Question question) {
        int selected = question.getSelectedIndex();
        int correct = question.getCorrectIndex();

        if (question.isCorrect()) {
            tvAnswerStatus.setText("Estado: correcta");
        } else {
            tvAnswerStatus.setText("Estado: incorrecta");
        }

        String pointsText = "Puntaje en esta pregunta: " + question.getPointsEarned();
        if (question.getStreakPoints() != 0) {
            pointsText += " (incluye racha: " + question.getStreakPoints() + ")";
        }
        tvQuestionPoints.setText(pointsText);

        Button selectedButton = getOptionButtonByIndex(selected);
        Button correctButton = getOptionButtonByIndex(correct);

        if (question.isCorrect()) {
            selectedButton.setBackgroundColor(Color.parseColor("#C8E6C9"));
        } else {
            selectedButton.setBackgroundColor(Color.parseColor("#FFCDD2"));
            correctButton.setBackgroundColor(Color.parseColor("#C8E6C9"));
        }

        disableOptionButtons();
    }

    private Button getOptionButtonByIndex(int index) {
        if (index == 0) return btnOption1;
        if (index == 1) return btnOption2;
        if (index == 2) return btnOption3;
        return btnOption4;
    }

    private void resetOptionStyles() {
        btnOption1.setEnabled(true);
        btnOption2.setEnabled(true);
        btnOption3.setEnabled(true);
        btnOption4.setEnabled(true);

        btnOption1.setBackgroundColor(Color.parseColor("#E0E0E0"));
        btnOption2.setBackgroundColor(Color.parseColor("#E0E0E0"));
        btnOption3.setBackgroundColor(Color.parseColor("#E0E0E0"));
        btnOption4.setBackgroundColor(Color.parseColor("#E0E0E0"));
    }

    private void disableOptionButtons() {
        btnOption1.setEnabled(false);
        btnOption2.setEnabled(false);
        btnOption3.setEnabled(false);
        btnOption4.setEnabled(false);
    }

    private void goToResult(int finalScore) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
        intent.putExtra(AppConstants.EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(AppConstants.EXTRA_FINAL_SCORE, finalScore);
        startActivity(intent);
        finish();
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
            Intent intent = new Intent(this, DifficultyActivity.class);
            intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.menu_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(AppConstants.EXTRA_PLAYER_NAME, playerName);
            intent.putExtra(AppConstants.EXTRA_FROM_QUIZ, true);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int calculateStreakPoints(boolean isCorrect) {
        if (isCorrect) {
            correctStreak++;
            incorrectStreak = 0;

            if (correctStreak >= 2) {
                return correctStreak - 1;
            } else {
                return 0;
            }
        } else {
            incorrectStreak++;
            correctStreak = 0;

            if (incorrectStreak >= 2) {
                return -(incorrectStreak - 1);
            } else {
                return 0;
            }
        }
    }

    private void useHint() {
        Question question = questions.get(currentQuestionIndex);

        if (question.isAnswered()) {
            return;
        }

        if (question.isHintUsed()) {
            return;
        }

        if (hintsUsedTotal >= 3) {
            return;
        }

        question.setHintUsed(true);
        hintsUsedTotal++;

        totalScore -= 2;
        tvScore.setText("Puntaje acumulado: " + totalScore);
        tvHintsInfo.setText("Pistas usadas: " + hintsUsedTotal + " de 3");

        hideTwoIncorrectOptions(question);
        btnHint.setEnabled(false);
    }

    private void hideTwoIncorrectOptions(Question question) {
        ArrayList<Integer> incorrectIndexes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (i != question.getCorrectIndex()) {
                incorrectIndexes.add(i);
            }
        }

        if (incorrectIndexes.size() >= 2) {
            getOptionButtonByIndex(incorrectIndexes.get(0)).setVisibility(Button.INVISIBLE);
            getOptionButtonByIndex(incorrectIndexes.get(1)).setVisibility(Button.INVISIBLE);
        }
    }
}
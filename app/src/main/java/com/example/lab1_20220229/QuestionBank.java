package com.example.lab1_20220229;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {

    public static ArrayList<Question> getQuestionsForGame(String selectedDifficulty) {
        ArrayList<Question> easyQuestions = getEasyQuestions();
        ArrayList<Question> hardQuestions = getHardQuestions();
        ArrayList<Question> result = new ArrayList<>();

        if (selectedDifficulty.equals(AppConstants.DIFFICULTY_EASY)) {
            Collections.shuffle(easyQuestions);
            for (int i = 0; i < 5; i++) {
                Question q = easyQuestions.get(i);
                q.shuffleOptions();
                result.add(q);
            }
        } else if (selectedDifficulty.equals(AppConstants.DIFFICULTY_HARD)) {
            Collections.shuffle(hardQuestions);
            for (int i = 0; i < 5; i++) {
                Question q = hardQuestions.get(i);
                q.shuffleOptions();
                result.add(q);
            }
        } else {
            ArrayList<Question> all = new ArrayList<>();
            all.addAll(easyQuestions);
            all.addAll(hardQuestions);
            Collections.shuffle(all);

            for (int i = 0; i < 5; i++) {
                Question q = all.get(i);
                q.shuffleOptions();
                result.add(q);
            }
        }

        return result;
    }

    private static ArrayList<Question> getEasyQuestions() {
        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "¿Qué programa espacial busca llevar astronautas nuevamente a la Luna?",
                createOptions("Artemis", "Apollo", "Gemini", "Mercury"),
                0,
                AppConstants.DIFFICULTY_EASY
        ));

        list.add(new Question(
                "¿Cuál es el satélite natural de la Tierra?",
                createOptions("La Luna", "Marte", "Europa", "Titán"),
                0,
                AppConstants.DIFFICULTY_EASY
        ));

        list.add(new Question(
                "¿Qué agencia lidera el programa Artemis?",
                createOptions("NASA", "ESA", "JAXA", "SpaceX"),
                0,
                AppConstants.DIFFICULTY_EASY
        ));

        list.add(new Question(
                "¿Qué vehículo se usa como cohete principal en Artemis?",
                createOptions("SLS", "Falcon 9", "Soyuz", "Starship"),
                0,
                AppConstants.DIFFICULTY_EASY
        ));

        list.add(new Question(
                "¿Artemis I llevó tripulación humana?",
                createOptions("No", "Sí", "Solo un piloto", "Solo turistas"),
                0,
                AppConstants.DIFFICULTY_EASY
        ));

        list.add(new Question(
                "¿Qué misión del programa Artemis fue una prueba sin tripulación alrededor de la Luna?",
                createOptions("Artemis I", "Artemis II", "Artemis III", "Apollo 11"),
                0,
                AppConstants.DIFFICULTY_EASY
        ));

        return list;
    }

    private static ArrayList<Question> getHardQuestions() {
        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "¿Cuál es la principal diferencia entre Artemis I y Artemis II?",
                createOptions(
                        "Artemis II lleva tripulación a bordo",
                        "Artemis II lleva carga útil comercial",
                        "Artemis II orbitará la Luna sin tripulación",
                        "Artemis II usará un cohete diferente al SLS"
                ),
                0,
                AppConstants.DIFFICULTY_HARD
        ));

        list.add(new Question(
                "¿Cuál es el objetivo principal de Artemis III?",
                createOptions(
                        "Llevar astronautas a la superficie lunar",
                        "Poner un satélite en Marte",
                        "Construir una estación espacial en Venus",
                        "Realizar turismo espacial comercial"
                ),
                0,
                AppConstants.DIFFICULTY_HARD
        ));

        list.add(new Question(
                "¿Qué nave transporta a la tripulación en el programa Artemis?",
                createOptions("Orion", "Dragon", "Crew Starliner", "Apollo CM"),
                0,
                AppConstants.DIFFICULTY_HARD
        ));

        list.add(new Question(
                "¿Qué componente forma parte de la arquitectura de Artemis para futuras misiones?",
                createOptions("Gateway", "Hubble", "Skylab", "Sputnik"),
                0,
                AppConstants.DIFFICULTY_HARD
        ));

        list.add(new Question(
                "¿Qué busca impulsar Artemis además de la exploración lunar?",
                createOptions(
                        "Preparar futuras misiones a Marte",
                        "Eliminar satélites en órbita baja",
                        "Reemplazar completamente la ISS",
                        "Suspender la exploración robótica"
                ),
                0,
                AppConstants.DIFFICULTY_HARD
        ));

        list.add(new Question(
                "¿Qué módulo se asocia principalmente al aterrizaje lunar en futuras misiones Artemis?",
                createOptions(
                        "Human Landing System",
                        "Voyager Module",
                        "Columbia Capsule",
                        "Lunar Shuttle Classic"
                ),
                0,
                AppConstants.DIFFICULTY_HARD
        ));

        return list;
    }

    private static ArrayList<String> createOptions(String op1, String op2, String op3, String op4) {
        ArrayList<String> options = new ArrayList<>();
        options.add(op1);
        options.add(op2);
        options.add(op3);
        options.add(op4);
        return options;
    }
}
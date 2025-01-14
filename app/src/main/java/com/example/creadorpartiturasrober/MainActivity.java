package com.example.creadorpartiturasrober;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonCompose;
    private Button buttonViewScores;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botones
        buttonCompose = findViewById(R.id.buttonCompose);
        buttonViewScores = findViewById(R.id.buttonViewScores);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Configurar acciones de los botones
        buttonCompose.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ComposeActivity.class);
            startActivity(intent);
        });

        buttonViewScores.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewScoresActivity.class);
            startActivity(intent);
        });

        buttonLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Terminar MainActivity para evitar volver atrás tras logout
        });
    }
}

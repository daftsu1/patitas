package com.example.a.proyectjaafunciono;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegisterActivity extends AppCompatActivity {

    Button registrarse;
    ImageButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registrarse = (Button) findViewById(R.id.button3);
        login = (ImageButton) findViewById(R.id.imageButton);

        //Botón Registro-Confirmar.
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(next);

            }
        });

        //Botón Atrás.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(next);
            }
        });
    }

    }


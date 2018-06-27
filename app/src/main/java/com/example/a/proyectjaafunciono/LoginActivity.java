package com.example.a.proyectjaafunciono;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button registrarse;

    EditText txtUsuario, txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.button);
        registrarse = (Button) findViewById(R.id.button2);
        txtUsuario = (EditText) findViewById(R.id.txtcorreo);
        txtContraseña = (EditText) findViewById(R.id.txtpassword);

        //Botón Login-Confirmar.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread tr = new Thread(){
                    @Override
                    public void run(){
                        final String res = enviarPost(txtUsuario.getText().toString(),txtContraseña.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int r = objJSON(res);
                                if (r>0){
                                    Intent i = new Intent(getApplicationContext(), Principal.class);
                                    startActivity(i);
                                    Intent next = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(next);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Datos de usuario Incorrectos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                tr.start();


            }
        });

        //Botón Registro.
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(next);
            }
        });
    }

    public String enviarPost(String cor, String pass) {
        String parametros = "cor=" + cor + "&pas=" + pass;
        HttpURLConnection connection = null;
        String respuesta = "";
        try {
            //ENVIA DATOS A LA WEB
            URL url = new URL("https://files.000webhost.com/prueba.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametros.getBytes().length));

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();

            //TOMA RESPUESTA PARA RECORRERLA
            Scanner inStream = new Scanner(connection.getInputStream());

            while (inStream.hasNextLine())
                respuesta += (inStream.nextLine());

        } catch (Exception e) {
        }
        //RETORNA RESPUES OBTENIDA DEL SERVICE WEB.

        return respuesta.toString();
    }

    public int objJSON(String rspta) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(rspta);
            if (json.length() > 0)
                res = 1;
        } catch (Exception e) {

        }
        return res;
    }
}

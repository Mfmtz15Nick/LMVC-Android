package com.nick.ropal.Login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nick.ropal.R;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements Login.View, View.OnClickListener {

    //Componentes de XML
    Button boton;
    TextInputLayout usuario, password;
    CheckBox checkBox;

    // Crearemos un objeto Presentador para mandarle
    public Login.Presentador presentador ;

    // Variables para poder guardar datos del usuario en el telefono.
    private static final String STRING_PREFERENCES = "Sesion";
    private static final String PREFERENCE_ESTADO_BUTTON_SESION = "Estado.Boton";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Preguntar si existe una sesión activa en el dispositivo para que no entre a Login
        if(obtenerSesion()){
            String user = obtenerUsuario();
            Intent intent = new Intent(getApplicationContext(),Principal.class);
            intent.putExtra("USUARIO",user);
            startActivity(intent);
            finish();
        }

        boton = findViewById(R.id.boton);
        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        checkBox = findViewById(R.id.checkbox);

        boton.setOnClickListener(this);

        presentador =  new Presentador(this);


    }
    @Override
    public void onClick(View view) {
        //Manda el usuario y contraseña al presentador para saber si son correctos los datos
        presentador.valida(usuario.getEditText().getText().toString().trim(),password.getEditText().getText().toString().trim(),boton);
    }

    //Metodo para guardar el Usuario en el dispositivo
    public void guardarSesion(String usuario, String Password, Button boton, boolean estadoBoton) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.commit();
    }


    //Vemos si existe alguna cuenta activa.
    public boolean obtenerSesion(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);
    }
    //Cambia el estado del boton
    public void setBoton() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,checkBox.isChecked()).apply();

    }

    //Obtenemos el Usuario que está guardado en el dispositivo
    public String obtenerUsuario(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        return preferences.getString("usuario","vacio");
    }


    @Override
    //Metodo que es llamado desde el controlador para mostrar alguna accion dependiendo de la respuesta que este nos dé.
    public void muestraSesion(boolean respuesta) {
        if (respuesta){
            if(checkBox.isChecked()){
                guardarSesion(usuario.getEditText().getText().toString().trim(),password.getEditText().getText().toString().trim(),boton,true);
                setBoton();
            }
            Intent intent = new Intent(MainActivity.this,Principal.class);
            intent.putExtra("USUARIO",usuario.getEditText().getText().toString().trim());
            startActivity(intent);
            finish();
        }
        else{
            Snackbar.make(boton, "Usuario o contraseña son incorrectos", Snackbar.LENGTH_SHORT).show();
        }
    }


}


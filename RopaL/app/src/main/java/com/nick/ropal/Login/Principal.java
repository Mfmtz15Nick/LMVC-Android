package com.nick.ropal.Login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nick.ropal.R;

public class Principal extends AppCompatActivity implements   View.OnLongClickListener {

    TextView eti ;
    private static final String STRING_PREFERENCES = "Sesion";
    private static final String PREFERENCE_ESTADO_BUTTON_SESION = "Estado.Boton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        eti = findViewById(R.id.eti);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null){
            String user = extras.getString("USUARIO");
            eti.setText("Hola "+user);
        }

        eti.setOnLongClickListener(this);

    }


    @Override
    public boolean onLongClick(View view) {
        eti.setText("B Y E :)");
        guardarSesion(false);
        Intent intent = new Intent(Principal.this,MainActivity.class);
        startActivity(intent);
        finish();
        return false;
    }

    public void guardarSesion( boolean estadoBoton) {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,estadoBoton).apply();

    }
}



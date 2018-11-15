package com.nick.ropal.Login;



import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Button;

public class Modelo implements Login.Modelo {

    Login.BD dataB = new BD();






    public boolean validaModelo(String usuario, String password, Button boton) {
        dataB.Checa(usuario, password, boton);
        boolean resp = dataB.getRespuesta();
        return resp;


    }






}


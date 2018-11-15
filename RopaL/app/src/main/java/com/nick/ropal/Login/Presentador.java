package com.nick.ropal.Login;



import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class Presentador implements Login.Presentador {

    public Login.View view;
    public Login.Modelo modelo = new Modelo();
    String usu = "m";
    String pas = "1";





    public Presentador(Login.View view){
        this.view = view;

    }


    @Override
    public void valida(String usuario, String password, Button boton) {
        if(modelo.validaModelo(usuario,password,boton)) {
             boton.setText("TRUE");
            view.muestraSesion(true);
        }
        else {
             boton.setText("FALSE");
            view.muestraSesion(false);
        }

    }






}


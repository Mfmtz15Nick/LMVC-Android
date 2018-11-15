package com.nick.ropal.Login;


import android.widget.Button;

public interface Login {

    interface View{
        void guardarSesion(String usuario, String Password, Button boton, boolean estadoBoton);
        boolean obtenerSesion();
        void muestraSesion(boolean respuesta);



    }

    interface Modelo{
        boolean validaModelo(String usuario, String password, Button boton);


    }
    interface Presentador{
        void valida(String usuario, String Password, Button boton);

    }
    interface BD{
        boolean Checa(String usuario, String password, Button boton);
        boolean getRespuesta();


    }



}


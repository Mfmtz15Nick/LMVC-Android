package com.nick.ropal.Login;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BD extends AppCompatActivity implements Login.BD  {
    boolean respuesta;

    @Override
    public boolean Checa(final String usuario, final String password, final Button boton) {
        Thread tr = new Thread() {
            @Override
            public void run() {
                try {
                    final String resultado = enviarDatosGet(usuario, password);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int r = obtenerDatosJson(resultado);
                            if (r > 0) {
                                respuesta = true;
                                setRespuesta(true);
                                boton.setText("Desde Modelo "+ respuesta);

                            } else {
                                respuesta = false;
                                boton.setText("Desde Modelo "+ respuesta);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        tr.start();
        //boton.setText("Desde return bd "+ respuesta);
        return getRespuesta();
    }



    public void setRespuesta(boolean res){
        this.respuesta=res;
    }
    public boolean getRespuesta(){
        return this.respuesta;
    }


    public String enviarDatosGet(String usu, String pas) throws IOException {
        //Url que voy almacenar
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;
        url = new URL("http://192.168.1.67/WebService/valida.php?usu="+usu+"&pas="+pas);
        //url = new URL("https://pelonchas-231194.000webhostapp.com/validaWeb2.php?usu="+usu+"&pas="+pas);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        respuesta = connection.getResponseCode();

        resul = new StringBuilder();

        if(respuesta== HttpURLConnection.HTTP_OK){
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((linea=reader.readLine())!= null){
                resul.append(linea);
            }
        }
        return resul.toString();


    }

    public int obtenerDatosJson(String response){
        int res = 0 ;
        try {

            JSONArray json = new JSONArray(response);
            if(json.length()>0)
                res = 1;

        }catch (Exception e){}

        return res;

    }
}

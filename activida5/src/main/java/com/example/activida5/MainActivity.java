package com.example.activida5;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.activida5.Adapters.ListaPersonajesAdapter;
import com.example.activida5.Objects.Personaje;
import com.example.milib.ListaFragment;
import com.example.milib.Tiempo;
import com.example.milib.TiempoListener;
import com.example.milib.Usuario;
import com.example.milib.asynctasks.HttpJsonAsyncTask;
import com.example.milib.asynctasks.HttpJsonAsyncTaskLista;
import com.example.milib.asynctasks.HttpJsonAsyncTaskLogin;
import com.example.milib.asynctasks.HttpJsonListener;
import com.example.milib.asynctasks.HttpJsonListenerLista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Tiempo tiempo;
    ListaFragment lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityEvents mainActivityEvents= new MainActivityEvents(this);

        tiempo = (Tiempo)getSupportFragmentManager().findFragmentById(R.id.fragmentTiempo);
        lista = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLista);

        tiempo.setListener(mainActivityEvents);






        /*HttpJsonAsyncTaskLogin httpJsonAsyncTaskLogin = new HttpJsonAsyncTaskLogin();
        Usuario aux = new Usuario("javi","123456");
        httpJsonAsyncTaskLogin.execute(aux);*/
    }
}
class MainActivityEvents implements TiempoListener, HttpJsonListener, HttpJsonListenerLista {

    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity){

        this.mainActivity=mainActivity;
    }
    @Override
    public void ponerTiempo() {
        Log.v("HttpJsonAsyncTask","PASE ");
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();

        transaction.show(mainActivity.tiempo);
        transaction.hide(mainActivity.lista);
        transaction.commit();
        HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s",
                "3117732","e5b16429b38526a2bbe739f85f653224");

        httpJsonAsyncTask.setListener(this);

        httpJsonAsyncTask.execute(url);

    }

    @Override
    public void Lista() {


        //
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();

        transaction.hide(mainActivity.tiempo);
        transaction.show(mainActivity.lista);
        transaction.commit();

        HttpJsonAsyncTaskLista httpJsonAsyncTaskLista = new HttpJsonAsyncTaskLista();
        httpJsonAsyncTaskLista.setListener(this);
        //cambiar a personajes
        httpJsonAsyncTaskLista.execute("http://10.0.2.2/Javi/GOT/leePersonajes.php");
    }

    @Override
    public void respuesta(String respuesta) {
        Log.v("HttpJsonAsyncTask","JSON: "+respuesta);
        try {
            JSONObject jsonGeneral = new JSONObject(respuesta);

            JSONObject coord = (JSONObject) jsonGeneral.get("coord");
            JSONArray tiempo = (JSONArray) jsonGeneral.get("weather");
            JSONObject aux = (JSONObject) tiempo.get(0);
            Log.v("HttpJsonAsyncTask","JSON: "+aux.get("description"));
            this.mainActivity.tiempo.lat.setText(coord.getString("lat"));
            this.mainActivity.tiempo.lon.setText(coord.getString("lon"));
            this.mainActivity.tiempo.tiempo.setText(aux.get("description").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void respuestaLista(String respuesta) {
        //Respuesta es un Json hay que acabar pasando un Array de personajes
        ArrayList array = new ArrayList<Personaje>();
        try {
            JSONObject jsonGeneral = new JSONObject(respuesta);

            JSONArray personajes = new JSONArray(jsonGeneral.get("personajes"));

            for (int i = 0; i<personajes.length();i++) {

                JSONObject personaje = (JSONObject) personajes.get(i);

                String Personaje = personaje.get("nombrePersonaje").toString();
                String Actor = personaje.get("nombreActor").toString();
                String Edad = personaje.get("edad").toString();
                String Altura = personaje.get("altura").toString();



                Personaje aux = new Personaje(Personaje,Actor,Edad,Altura);
                array.add(aux);

            }


            ListaPersonajesAdapter listaPersonajesAdapter=new ListaPersonajesAdapter(array);
            mainActivity.lista.recyclerView.setAdapter(listaPersonajesAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
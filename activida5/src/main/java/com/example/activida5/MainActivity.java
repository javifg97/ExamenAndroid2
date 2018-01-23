package com.example.activida5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.milib.Tiempo;
import com.example.milib.TiempoListener;
import com.example.milib.Usuario;
import com.example.milib.asynctasks.HttpJsonAsyncTask;
import com.example.milib.asynctasks.HttpJsonAsyncTaskLogin;

public class MainActivity extends AppCompatActivity {

    Tiempo tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityEvents mainActivityEvents= new MainActivityEvents(this);

        tiempo = (Tiempo)getSupportFragmentManager().findFragmentById(R.id.);




        /*HttpJsonAsyncTaskLogin httpJsonAsyncTaskLogin = new HttpJsonAsyncTaskLogin();
        Usuario aux = new Usuario("javi","123456");
        httpJsonAsyncTaskLogin.execute(aux);*/
    }
}
class MainActivityEvents implements TiempoListener{

    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity){

        this.mainActivity=mainActivity;
    }
    @Override
    public void ponerTiempo() {
        HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s",
                "3117732","e5b16429b38526a2bbe739f85f653224");
        httpJsonAsyncTask.execute(url);

    }
}
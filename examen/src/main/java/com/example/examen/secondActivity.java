package com.example.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.examen.Adapters.ListaNoticiasAdapter;
import com.example.examen.FBObjects.FBNoticia;
import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

public class secondActivity extends AppCompatActivity {

        ListaFragment listaFragmennoticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondActivityEvents events =new secondActivityEvents(this);

        listaFragmennoticias =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.frgLista);

        DataHolder.instance.fireBaseAdmin.setListener(events);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("noticias");


    }
}
class secondActivityEvents implements FireBaseAdminListener{
    secondActivity secondActivity;

    public secondActivityEvents(secondActivity secondActivity){
        this.secondActivity = secondActivity;
    }
    //En este metodo recibimos los datos que hemos descargado de la rama siendo null si ha habido algun problema
    @Override
    public void firebaseAdmin_ramaDescargada(String rama, DataSnapshot dataSnapshot) {
        if(rama.equals("noticias")){
            //Organizamos los datos en forma de ArrayList de noticias
            GenericTypeIndicator<ArrayList<FBNoticia>> indicator=new GenericTypeIndicator<ArrayList<FBNoticia>>(){};
            ArrayList<FBNoticia> noticias=dataSnapshot.getValue(indicator);

            //Creamos el adapter y le pasamos los datos descargados

            ListaNoticiasAdapter listaNoticiasAdapter=new ListaNoticiasAdapter(noticias, secondActivity);
            //seteamos al RecyclerView el adapter que acabamos de crear y rrellenado de datos
            secondActivity.listaFragmennoticias.recyclerView.setAdapter(listaNoticiasAdapter);
        }


    }

    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {

    }


}

package com.example.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.ListaFragment;

public class secondActivity extends AppCompatActivity {

        ListaFragment listaFragmennoticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondActivityEvents events =new secondActivityEvents(this);

        listaFragmennoticias =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.frgLista);

        DataHolder.instance.fireBaseAdmin.setListener(events);
        

    }
}
class secondActivityEvents implements FireBaseAdminListener{
    secondActivity secondActivity;

    public secondActivityEvents(secondActivity secondActivity){
        this.secondActivity = secondActivity;
    }

    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {

    }
}

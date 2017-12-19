 package com.example.guille.apptutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guille.apptutorial.Adapters.ListaCochesAdapter;
import com.example.guille.apptutorial.Adapters.ListaMensajesAdapter;
import com.example.guille.apptutorial.FBObjects.FBCoche;
import com.example.guille.apptutorial.FBObjects.Mensaje;
import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;


 public class SecondActivity extends AppCompatActivity {

     ListaFragment listaFragmentMensajes, listaFragmentCoches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events=new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        listaFragmentMensajes =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListMensajes);
        listaFragmentCoches =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);

        //PASAMOS POR PARAMETRO EL NOMBRE DE LA RAMA QUE QUIERAS EN LA BASE DE DATOS DE FIREBASE
        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("mensajes");
        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");

        Log.v("SecondActivity","--------EMAAAIL: "+DataHolder.instance.fireBaseAdmin.user.getEmail());


      /*  ArrayList<String> mdatos= new ArrayList<>();
        mdatos.add("MENSAJE1");
        mdatos.add("MENSAJE2");

        ListaMensajesAdapter listaMensajesAdapter = new ListaMensajesAdapter(mdatos);
        listaFragment.recyclerView.setAdapter(listaMensajesAdapter); */
    }
}


class SecondActivityEvents implements FireBaseAdminListener{

     SecondActivity secondActivity;

     public SecondActivityEvents(SecondActivity secondActivity){
         this.secondActivity=secondActivity;

     }
    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_ramaDescargada(String rama, DataSnapshot dataSnapshot) {
        Log.v("RAMA",rama+"--------RAMA DESCARGADA............"+dataSnapshot);

        if(rama.equals("mensajes")){

            //FIREBASE COGE EL VALOR Y LO INTENTA METER EN EL OBJETO mensaje
            //Mensaje mensaje=dataSnapshot.getValue(Mensaje.class);

       GenericTypeIndicator<Map<String,Mensaje>> indicator=new GenericTypeIndicator<Map<String,Mensaje>>(){};
        Map<String,Mensaje> msg=dataSnapshot.getValue(indicator);
        //VALUES NO ES UN ARRAY LIST ES UN COLLECTIONS
        Log.v("mensaje","MENSAJE CONTIENE: "+msg.values());

        //PARA TRANSFORMAR UN COLLECTION A UN ARRAY LIST HAY QUE HACER es new ArrayList<Mensaje>(msg.values())
        ListaMensajesAdapter listaMensajesAdapter=new ListaMensajesAdapter(new ArrayList<Mensaje>(msg.values()));
        secondActivity.listaFragmentMensajes.recyclerView.setAdapter(listaMensajesAdapter);

        }else if(rama.equals("Coches")){

            GenericTypeIndicator<ArrayList<FBCoche>> indicator=new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            ArrayList<FBCoche> coches=dataSnapshot.getValue(indicator);
            //VALUES NO ES UN ARRAY LIST ES UN COLLECTIONS
            Log.v("coches","COCHES CONTIENE: "+coches);

            //PARA TRANSFORMAR UN COLLECTION A UN ARRAY LIST HAY QUE HACER es new ArrayList<Mensaje>(msg.values())
            ListaCochesAdapter listaCochesAdapter=new ListaCochesAdapter(coches);
            secondActivity.listaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);
        }


    }
}
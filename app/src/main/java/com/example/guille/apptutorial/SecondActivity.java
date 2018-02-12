 package com.example.guille.apptutorial;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guille.apptutorial.Adapters.ListaCochesAdapter;
import com.example.guille.apptutorial.Adapters.ListaMensajesAdapter;
import com.example.guille.apptutorial.FBObjects.FBCoche;
import com.example.guille.apptutorial.FBObjects.Mensaje;
import com.example.guille.apptutorial.sqlLiteAdmin.Cochesql;
import com.example.guille.apptutorial.sqlLiteAdmin.DatabaseHandler;
import com.example.milib.ListaFragment;
import com.example.milib.asynctasks.HttpAsyncTask;
import com.example.milib.asynctasks.HttpJsonAsyncTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


 public class SecondActivity extends AppCompatActivity {

     ListaFragment /*listaFragmentMensajes,*/ listaFragmentCoches;
     SupportMapFragment mapFragment;
     DatabaseHandler databaseHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events=new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        //listaFragmentMensajes =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListMensajes);
        listaFragmentCoches =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);

        //PASAMOS POR PARAMETRO EL NOMBRE DE LA RAMA QUE QUIERAS EN LA BASE DE DATOS DE FIREBASE
        //DataHolder.instance.fireBaseAdmin.descargarYObservarRama("mensajes");


        //Log.v("SecondActivity","--------EMAAAIL: "+DataHolder.instance.fireBaseAdmin.user.getEmail());

        mapFragment.getMapAsync(events);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //PARA QUE NOS SALGA PRIMERO EL LOGIN
        transaction.show(listaFragmentCoches);
        transaction.hide(mapFragment);
        transaction.commit();

        /*HttpAsyncTask httpAsyncTask =new HttpAsyncTask();
        httpAsyncTask.execute("https://www.planwallpaper.com/static/images/general-night-golden-gate-bridge-hd-wallpapers-golden-gate-bridge-wallpaper.jpg",
                "https://www.planwallpaper.com/static/images/general-night-golden-gate-bridge-hd-wallpapers-golden-gate-bridge-wallpaper.jpg",
                "https://i.imgur.com/FsXaGSg.jpg",
                "https://i.imgur.com/FsXaGSg.jpg",
                "https://www.planwallpaper.com/static/images/general-night-golden-gate-bridge-hd-wallpapers-golden-gate-bridge-wallpaper.jpg");*/

        /*HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        httpJsonAsyncTask.execute("http://10.0.2.2/Javi/GOT/leePersonajes.php");*/
        databaseHandler = new DatabaseHandler(this);
    }
}


class SecondActivityEvents implements FireBaseAdminListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

     SecondActivity secondActivity;
    GoogleMap mMap;
    ArrayList<FBCoche> coches;
    List<Cochesql> cocheList;

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
/*
            //FIREBASE COGE EL VALOR Y LO INTENTA METER EN EL OBJETO mensaje
            //Mensaje mensaje=dataSnapshot.getValue(Mensaje.class);

       GenericTypeIndicator<Map<String,Mensaje>> indicator=new GenericTypeIndicator<Map<String,Mensaje>>(){};
        Map<String,Mensaje> msg=dataSnapshot.getValue(indicator);
        //VALUES NO ES UN ARRAY LIST ES UN COLLECTIONS
        Log.v("mensaje","MENSAJE CONTIENE: "+msg.values());

        //PARA TRANSFORMAR UN COLLECTION A UN ARRAY LIST HAY QUE HACER es new ArrayList<Mensaje>(msg.values())
        ListaMensajesAdapter listaMensajesAdapter=new ListaMensajesAdapter(new ArrayList<Mensaje>(msg.values()));
        secondActivity.listaFragmentMensajes.recyclerView.setAdapter(listaMensajesAdapter);*/

        }else if(rama.equals("Coches")){
            quitarPines();
            GenericTypeIndicator<ArrayList<FBCoche>> indicator=new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            coches=dataSnapshot.getValue(indicator);
            //VALUES NO ES UN ARRAY LIST ES UN COLLECTIONS
            Log.v("coches","COCHES CONTIENE: "+coches);
            int cont = 0;
            for (FBCoche coche: coches) {
                Cochesql cocheaux = new Cochesql(cont,coche.Fabricado,coche.Marca,coche.Nombre,coche.lat,coche.lon);
                this.secondActivity.databaseHandler.addContact(cocheaux);
                cont++;
            }

            //PARA TRANSFORMAR UN COLLECTION A UN ARRAY LIST HAY QUE HACER es new ArrayList<Mensaje>(msg.values())
            ListaCochesAdapter listaCochesAdapter=new ListaCochesAdapter(coches);
            secondActivity.listaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);

            agregarPinesCoches();

            cocheList = secondActivity.databaseHandler.getAllContacts();

            for (Cochesql coche: cocheList) {
                Log.v("SQLDDBB","FABRICADO----->"+coche.getFabricado());
                Log.v("SQLDDBB","MARCA----->"+coche.getMarca());
                Log.v("SQLDDBB","NOMBRE----->"+coche.getNombre());
            }

        }


    }

    public void quitarPines(){
         if(coches != null){
             //Log.v("coches1234","Entro en borrar");
             for(int i = 0; i<coches.size();i++) {
                 FBCoche cocheTemp = coches.get(i);
               //  Log.v("coches1234","Antes del if");
                 if(cocheTemp.getMarker()!=null){
                 //    Log.v("coches1234","No es el if");
                     cocheTemp.getMarker().remove();
                 }
             }
         }
    }
    public void agregarPinesCoches(){

         for(int i = 0; i<coches.size();i++){
             FBCoche cocheTemp = coches.get(i);

             LatLng PosTemp = new LatLng(cocheTemp.lat, cocheTemp.lon);
             MarkerOptions markerOptions = new MarkerOptions();
             markerOptions.position(PosTemp);
             markerOptions.title(cocheTemp.Nombre);
             if(mMap!=null){
                 Marker marker = mMap.addMarker(markerOptions);
                 marker.setTag(cocheTemp);
                 cocheTemp.setMarker(marker);
                 if (i==0){
                     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PosTemp, 7));
                 }
             }
         }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        FBCoche coche= (FBCoche) marker.getTag();
        Log.v("EYO","Pin "+ coche.Nombre);
        return false;
    }
}
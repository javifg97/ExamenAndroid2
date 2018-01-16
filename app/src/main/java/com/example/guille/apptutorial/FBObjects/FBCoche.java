package com.example.guille.apptutorial.FBObjects;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by guille on 18/12/17.
 */
@IgnoreExtraProperties
public class FBCoche {

    public int Fabricado;
    public String Marca;
    public String Nombre;
    public double lat;
    public double lon;

    public Marker marker=null;

    public FBCoche(){

    }

    public FBCoche(int Fabricado,String Marca,String Nombre,double lat,double lon){
        this.Fabricado=Fabricado;
        this.Marca=Marca;
        this.Nombre=Nombre;
        this.lat=lat;
        this.lon=lon;

    }

    public void setMarker(Marker marker){
        this.marker = marker;
    }
    public Marker getMarker(){
        return marker;
    }
}


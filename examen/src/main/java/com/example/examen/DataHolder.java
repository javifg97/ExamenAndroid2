package com.example.examen;

/**
 * Created by javier.fernandez3 on 19/12/2017.
 */

public class DataHolder {
    public static DataHolder instance = new DataHolder();

    //Creamos una referencia a firebaseadmin para poder utilizarlo en cualquier clase de nuestro modulo
    public FireBaseAdmin fireBaseAdmin;

    public DataHolder(){
        fireBaseAdmin=new FireBaseAdmin();
    }
}

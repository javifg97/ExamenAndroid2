package com.example.guille.apptutorial.FBObjects;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by guille on 18/12/17.
 */
@IgnoreExtraProperties

public class Mensaje {

    public String original;

    public Mensaje(){

    }

    public Mensaje(String original){
        this.original=original;
    }

}

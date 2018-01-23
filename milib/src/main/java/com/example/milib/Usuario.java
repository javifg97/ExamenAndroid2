package com.example.milib;

/**
 * Created by javier.fernandez3 on 23/01/2018.
 */

public class Usuario {

    String usuario;
    String pass;

    public Usuario(String us, String pas){
        this.usuario = us;
        this.pass = pas;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }
}

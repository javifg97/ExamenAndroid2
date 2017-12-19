package com.example.examen;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by javier.fernandez3 on 19/12/2017.
 */

public class FireBaseAdmin {
    private FirebaseAuth mAuth;

    public FireBaseAdminListener listener;
    public FirebaseUser user;

    public FireBaseAdmin(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void setListener(FireBaseAdminListener listener){
        this.listener=listener;
    }

    public void registroEmailYPass(String email, String password, Activity activity){

    }
    public void loginEmailYPass(String email, String password, Activity activity){

    }

}

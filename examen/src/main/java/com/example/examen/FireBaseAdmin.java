package com.example.examen;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // Sign in success, update UI with the signed-in user's information


                        if (task.isSuccessful()) {

                            //Guardamos la informacion del usuario que se acaba de crear en nuestro usuario que sera accesible a traves del DataHolder
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            listener.firebaseAdmin_registerOk(true);

                        }else{
                            listener.firebaseAdmin_registerOk(false );
                        }

                        // ...
                    }
                });
    }
    public void loginEmailYPass(String email, String password, Activity activity){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // Sign in success, update UI with the signed-in user's information


                        if (task.isSuccessful()) {

                            //Guardamos la informacion del usuario que se acaba de logear en nuestro usuario que sera accesible a traves del DataHolder
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            listener.firebaseAdmin_loginOk(true);

                        }else{
                            listener.firebaseAdmin_loginOk(false );
                        }

                        // ...
                    }
                });
    }

}

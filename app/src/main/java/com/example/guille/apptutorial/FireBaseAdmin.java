package com.example.guille.apptutorial;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by guille on 16/12/17.
 */

//HAY QUE AGREGAR COSAS EN GRADLE SCRIPTS BUILD.GRADLE APPTUTORIAL Y MODULE APP

public class FireBaseAdmin {

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRefRaiz;

    public FireBaseAdminListener listener;
    public FirebaseUser user;

    public FireBaseAdmin(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefRaiz=database.getReference();
    }

    public void setListener(FireBaseAdminListener listener){
        this.listener=listener;
    }

    public void registerEmailPass(String email, String password, Activity activity){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                            // Sign in success, update UI with the signed-in user's information


                        if (task.isSuccessful()) {
                            //CREAMOS UN USUARIO
                            Log.d("Firebase", "createUserWithEmail:success" + task.isSuccessful());
                            user=FirebaseAuth.getInstance().getCurrentUser();
                           listener.firebaseAdmin_registerOk(true);

                        }else{
                            listener.firebaseAdmin_registerOk(false );
                        }

                        // ...
                    }
                });

    }
    public void loginEmailPass(String email, String password, Activity activity) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Log.d("firebaselogin", "signInWithEmail:success");
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    listener.firebaseAdmin_loginOk(true);

                } else {
                    // If sign in fails, display a message to the user.
                    listener.firebaseAdmin_loginOk(false);
                    Log.w(TAG, "signInWithEmail:failure", task.getException());


                }

                // ...
            }
        });

    }
    public void descargarYObservarRama(final String rama){

        //PARA LEER CUALQUIER RAMA QUE ESTES INTERESADO EN RECIBIR SE LE PASA LA RUTA POR PARAMETRO Y YA ESTA
        DatabaseReference  refRama = myRefRaiz.child(rama);
// Read from the database
        refRama.addValueEventListener(new ValueEventListener() {
            @Override

            //dataSnapshot ES UN HASH MAP que representa la rama
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listener.firebaseAdmin_ramaDescargada(rama,dataSnapshot );

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                listener.firebaseAdmin_ramaDescargada(rama,  null);
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}

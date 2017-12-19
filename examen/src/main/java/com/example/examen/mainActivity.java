package com.example.examen;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;

public class mainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityEvents mainActivityEvents= new MainActivityEvents(this);

        //vinculamos los fragments con los que hemos añadido en el xml
        loginFragment=(LoginFragment)getSupportFragmentManager().findFragmentById(R.id.frgLogin );
        registerFragment=(RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.frgRegistro );

        //Les indicamos quien va a administrar sus eventos y los va a escuchar
        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);


        //En la transaction ponemos cual queremos que salga primero(la que se muestra)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();
    }
}
//Implementamos todos los listeners ya que esta clase es la que escucha
class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener{
    mainActivity mainActivity;

    public MainActivityEvents(mainActivity mainActivity){
        this.mainActivity=mainActivity;
    }


    @Override
    public void registerFragmentRegisterButtonAceptarClicked(String sUser, String sPass) {
        //llamamos a los metodos de FirebaseAdmin para registrar y nos notificara por el metodo firebaseAdmin_registerOk
        DataHolder.instance.fireBaseAdmin.registroEmailYPass(sUser,sPass,mainActivity);
    }

    @Override
    public void registerFragmentRegisterButtonCancelarClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(mainActivity.loginFragment);
        transaction.hide(mainActivity.registerFragment);
        transaction.commit();
    }

    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPass) {
        //llamamos a los metodos de FirebaseAdmin para logear y nos notificara por el metodo firebaseAdmin_loginOk
        DataHolder.instance.fireBaseAdmin.loginEmailYPass(sUser,sPass,mainActivity);
    }

    @Override
    public void loginFragmentRegisterButtonClicked() {
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(mainActivity.loginFragment);
        transaction.show(mainActivity.registerFragment);
        transaction.commit();
    }

    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {
        if(blOk){
            //iniciamos el second activity y cerramos el main
            Intent intent= new Intent(mainActivity,secondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();

        }else{
            Log.v("LoginError","Mal el registro"+blOk);
        }
    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {
        if(blOk){
            //iniciamos el second activity y cerramos el main
            Intent intent= new Intent(mainActivity,secondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();

        }else{
            Log.v("LoginError","Mal el login"+blOk);
        }
    }
}

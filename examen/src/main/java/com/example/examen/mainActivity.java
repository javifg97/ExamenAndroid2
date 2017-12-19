package com.example.examen;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    }

    @Override
    public void registerFragmentRegisterButtonCancelarClicked() {

    }

    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPass) {

    }

    @Override
    public void loginFragmentRegisterButtonClicked() {

    }

    @Override
    public void firebaseAdmin_registerOk(boolean blOk) {

    }

    @Override
    public void firebaseAdmin_loginOk(boolean blOk) {

    }
}

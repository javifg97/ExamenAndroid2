package com.example.guille.apptutorial;


import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;


import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    //FireBaseAdmin fireBaseAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment=(LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentLogin );
        registerFragment=(RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentRegister );

        //INICIALIZAMOS FIREBASE
            //fireBaseAdmin = new FireBaseAdmin();
        //INICIALIZAMOS EL MAINACTIVITYEVENTS
        MainActivityEvents mainActivityEvents= new MainActivityEvents(this);




        //PARA QUE EL MAINACTIVITY ESCUCHE DEL LOGINFRAGMENT,REGISTER Y FIREBASEADMIN
        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        //CREAMOS LAS TRANSICIONES

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //PARA QUE NOS SALGA PRIMERO EL LOGIN
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();

        //  SALTARSE LOGIN
        //DataHolder.instance.fireBaseAdmin.loginEmailPass("guille@guille.com","1234567890",this);


    }
}

 class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener{
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    //CUANDO ENTRE POR AQUI ES QUE LE DIERON AL BOTON LOGIN QUE LO LLEGA A SABER POR LA IMPLEMENTACION DEL LOGINFRAGMENTLISTENER

     @Override
     public void loginFragmentLoginButtonClicked(String sUser, String sPass) {
         DataHolder.instance.fireBaseAdmin.loginEmailPass(sUser,sPass,mainActivity  );
     }
    //CUANDO ENTRE POR AQUI ES QUE LE DIERON AL BOTON REGISTRAR QUE LO LLEGA A SABER POR LA IMPLEMENTACION DEL LOGINFRAGMENTLISTENER

     @Override
     public void loginFragmentRegisterButtonClicked() {
        //CREAMOS LAS TRANSICIONES
         Log.v("registro","REEEEEEEEGIIIIIIIIISSSSSSTROOOOOOOOO");

         FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();

         //PARA QUE CUANDO RECIBAMOS QUE SE HA PULSADO EL BOTON DE REGISTRO SE OCULTE EL FRAGMENT DEL LOGIN Y SE MUESTRE EL REGISTRO
         transaction.hide(mainActivity.loginFragment);
         transaction.show(mainActivity.registerFragment);
         transaction.commit();
     }

     @Override
     public void registerFragmentRegisterButtonAceptarClicked(String sUser, String sPass) {
         Log.v("tranAceptar","TODO CORRECTO");
         DataHolder.instance.fireBaseAdmin.registerEmailPass(sUser,sPass,mainActivity);
     }

     @Override
     public void registerFragmentRegisterButtonCancelarClicked() {

         FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();

         transaction.show(mainActivity.loginFragment);
         transaction.hide(mainActivity.registerFragment);
         transaction.commit();
         Log.v("tran","TODO CORRECTO");
     }

     @Override
     public void firebaseAdmin_registerOk(boolean blOk) {

        if(blOk){
            Log.v("registroOk","TODO CORRECTO"+blOk);
            //ESTA PARTE DE CODIGO LO QUE HARA ES INICIAR EL SEGUNDO ACTIVITY Y FINALIZAR EL MAIN
            Intent intent= new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
     }

     @Override
     public void firebaseAdmin_loginOk(boolean blOk) {

         if(blOk){
             Log.v("loginOk","TODO CORRECTO"+blOk);
             //ESTA PARTE DE CODIGO LO QUE HARA ES INICIAR EL SEGUNDO ACTIVITY Y FINALIZAR EL MAIN
             Intent intent= new Intent(mainActivity,SecondActivity.class);
             mainActivity.startActivity(intent);
             mainActivity.finish();

         }else{
             Log.v("LoginError","TODO MAL"+blOk);
         }
     }

     @Override
     public void firebaseAdmin_ramaDescargada(String rama, DataSnapshot dataSnapshot) {

     }
 }
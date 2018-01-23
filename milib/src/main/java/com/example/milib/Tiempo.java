package com.example.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tiempo extends Fragment {

    public EditText lat, lon, tiempo;
    Button btn, btnLista;
    TiempoEvents events;
    TiempoListener listener;


    public Tiempo() {
        // Required empty public constructor
    }

    public void setListener(TiempoListener listener){
        this.listener=listener;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tiempo, container, false);

        lat = v.findViewById(R.id.lblLat);
        lon = v.findViewById(R.id.lblLon);
        tiempo = v.findViewById(R.id.lblTiempo);
        btn = v.findViewById(R.id.btnTiempo);
        btnLista = v.findViewById(R.id.btnLista);

        events = new TiempoEvents(this);
        btn.setOnClickListener(events);
        btnLista.setOnClickListener(events);

        return v;
    }

}
class TiempoEvents implements View.OnClickListener {

    Tiempo tiempo;

    public TiempoEvents(Tiempo tiempo) {

        this.tiempo = tiempo;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.tiempo.btn.getId()) {
            this.tiempo.listener.ponerTiempo();

        } else if(v.getId() == this.tiempo.btnLista.getId()){
            this.tiempo.listener.Lista();
        }
    }
}

package com.example.guille.apptutorial.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guille.apptutorial.FBObjects.Mensaje;
import com.example.guille.apptutorial.R;

import java.util.ArrayList;

/**
 * Created by javier.fernandez3 on 17/12/17.
 */

//POR PARAMETRO LE METEMOS EL VIEWHOLDER QUE ES EL MOLDE FIJO EN EL CUAL SE VA A UTILIZAR COMO REFERENCIA PARA PINTAR LAS CELDAS
    //ES LA CLASE CELDA CON SU XML ASOCIADO

public class ListaMensajesAdapter extends RecyclerView.Adapter<MensajeViewHolder> {

    private ArrayList<Mensaje> mensajes;

    public ListaMensajesAdapter(ArrayList<Mensaje> mensajes){

        this.mensajes=mensajes;
    }


    @Override
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_mensaje_layout,null);
        MensajeViewHolder mensajeViewHolder= new MensajeViewHolder(view);
        return mensajeViewHolder;
    }

    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {
        holder.textomensaje.setText(mensajes.get(position).original);
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }
}
 class MensajeViewHolder extends RecyclerView.ViewHolder{

     public TextView textomensaje;

     public MensajeViewHolder(View itemView) {
         super(itemView);
         textomensaje=itemView.findViewById(R.id.textomensaje);
     }
 }
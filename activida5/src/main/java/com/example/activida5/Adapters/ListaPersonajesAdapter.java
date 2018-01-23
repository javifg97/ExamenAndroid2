package com.example.activida5.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activida5.Objects.Personaje;
import com.example.activida5.R;

import java.util.ArrayList;

/**
 * Created by javier on 23/01/2018.
 */

public class ListaPersonajesAdapter extends RecyclerView.Adapter<PersonajeViewHolder> {


    private ArrayList<Personaje> personajes;

    public ListaPersonajesAdapter(ArrayList<Personaje> personajes){

        this.personajes=personajes;
    }

    @Override
    public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_personajes_layout,null);
        PersonajeViewHolder personajeViewHolder= new PersonajeViewHolder(view);
        return personajeViewHolder;    }

    @Override
    public void onBindViewHolder(PersonajeViewHolder holder, int position) {
        // holder.textomensaje.setText(coches.get(position).original);

        //IMPORTANTE ME COGE FABRICADO POR QUE SE CREE QUE ES UN ID
        holder.lblPersonaje.setText(personajes.get(position).personaje);
        holder.lblActor.setText(personajes.get(position).actor);
        holder.lblEdad.setText(personajes.get(position).edad);
        holder.lblAltura.setText(personajes.get(position).altura);

    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }
}
class PersonajeViewHolder extends RecyclerView.ViewHolder{

    public TextView lblPersonaje;
    public TextView lblActor;
    public TextView lblEdad;
    public TextView lblAltura;


    public PersonajeViewHolder(View itemView) {
        super(itemView);
        lblPersonaje=itemView.findViewById(R.id.lblPersonaje);
        lblActor=itemView.findViewById(R.id.lblActor);
        lblEdad=itemView.findViewById(R.id.lblEdad);
        lblAltura=itemView.findViewById(R.id.lblAltura);


    }
}

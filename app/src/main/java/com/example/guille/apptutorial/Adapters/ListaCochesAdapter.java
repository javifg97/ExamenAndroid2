package com.example.guille.apptutorial.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guille.apptutorial.FBObjects.FBCoche;
import com.example.guille.apptutorial.FBObjects.Mensaje;
import com.example.guille.apptutorial.R;

import java.util.ArrayList;

/**
 * Created by javier.fernandez3 on 18/12/17.
 */

public class ListaCochesAdapter extends RecyclerView.Adapter<CocheViewHolder> {

    private ArrayList<FBCoche> coches;

    public ListaCochesAdapter(ArrayList<FBCoche> coches){

        this.coches=coches;
    }


    @Override
    public CocheViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_coche_layout,null);
        CocheViewHolder cocheViewHolder= new CocheViewHolder(view);
        return cocheViewHolder;
    }

    //DONDE SE VINCULA ENTRE LOS CONTENIDOS Y EL CONTENEDOR(CELDAVIEWHOLDER);

    @Override
    public void onBindViewHolder(CocheViewHolder holder, int position) {
       // holder.textomensaje.setText(coches.get(position).original);

        //IMPORTANTE ME COGE FABRICADO POR QUE SE CREE QUE ES UN ID
        holder.tvfabricado.setText(coches.get(position).Fabricado+"");
        holder.tvnombre.setText(coches.get(position).Nombre);
        holder.tvmarca.setText(coches.get(position).Marca);
        holder.tvlat.setText(coches.get(position).lat+"");
        holder.tvlon.setText(coches.get(position).lon+"");

    }

    @Override
    public int getItemCount() {
        return coches.size();
    }
}
class CocheViewHolder extends RecyclerView.ViewHolder{

    public TextView tvfabricado;
    public TextView tvmarca;
    public TextView tvnombre;
    public TextView tvlat;
    public TextView tvlon;


    public CocheViewHolder(View itemView) {
        super(itemView);
        tvfabricado=itemView.findViewById(R.id.tvfabricado);
        tvmarca=itemView.findViewById(R.id.tvmarca);
        tvnombre=itemView.findViewById(R.id.tvnombre);
        tvlat=itemView.findViewById(R.id.tvlat);
        tvlon=itemView.findViewById(R.id.tvlon);

    }
}
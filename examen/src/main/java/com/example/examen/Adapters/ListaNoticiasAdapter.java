package com.example.examen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examen.FBObjects.FBNoticia;
import com.example.examen.R;

import java.util.ArrayList;

/**
 * Created by javier.fernandez3 on 19/12/2017.
 */

public class ListaNoticiasAdapter extends RecyclerView.Adapter<NoticiaViewHolder>{

    private ArrayList<FBNoticia> noticias;
    private Context mContexto;

    public ListaNoticiasAdapter(ArrayList<FBNoticia> noticias, Context mContexto){

        this.noticias=noticias;
        this.mContexto = mContexto;
    }
    //Inflamos la celda modelo y asignamos que ViewHolder tiene este RecycleView
    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_noticia_layout,null);
        NoticiaViewHolder noticiaViewHolder= new NoticiaViewHolder(view);
        return noticiaViewHolder;
    }
    //Rellenamos cada elemento de la celda tipo con lo descargado
    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {
        holder.tvNombre.setText(noticias.get(position).Nombre);
        Glide.with(mContexto).load(noticias.get(position).notUrl)
                .into(holder.ivImagen);


    }
        //el numero de celdas se ajusta al tamaño de lo descargado
    @Override
    public int getItemCount() {
        return noticias.size();
    }
}

//Este va a ser el molde para cada celda
class NoticiaViewHolder  extends RecyclerView.ViewHolder{
    public TextView tvNombre;
    public ImageView ivImagen;


    public NoticiaViewHolder(View itemView) {
        super(itemView);
        this.tvNombre=itemView.findViewById(R.id.tvNombre);
        this.ivImagen=itemView.findViewById(R.id.ivImagen);
    }
}

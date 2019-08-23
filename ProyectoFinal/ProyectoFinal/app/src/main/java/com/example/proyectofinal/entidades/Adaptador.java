package com.example.proyectofinal.entidades;

import android.content.Context;
import android.media.Image;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.example.proyectofinal.MainActivity;
import com.example.proyectofinal.R;


public class Adaptador extends BaseAdapter {
    Context contexto;
    List<Prenda> ListaPrendas;

    public Adaptador(Context contexto, List<Prenda> listaPrendas) {
        this.contexto = contexto;
        ListaPrendas = listaPrendas;
    }

    @Override
    public int getCount() {
        return ListaPrendas.size(); //retorna la cantidad de comentarios de la lista
    }

    @Override
    public Object getItem(int position) {
        return ListaPrendas.get(position); //retorna el objeto de la lista que esta en la posicion "position"
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewParent) {
        Log.d("MostrarLista", "Llega al adaptador");
        View vista;
        Log.d("MostrarLista", "Aca todavia no se rompio");
        LayoutInflater inflador = LayoutInflater.from(contexto);
        Log.d("MostrarLista", "Infla la view");
        vista = inflador.inflate(R.layout.listview, null);
        Log.d("MostrarLista", "Encuentra la listview");
        ImageView image = (ImageView) vista.findViewById(R.id.imgPrenda);
        TextView txtTipo = (TextView) vista.findViewById(R.id.txtTipo);
        TextView txtTemporada = (TextView) vista.findViewById(R.id.txtTemporada);
        TextView txtOcasion = (TextView) vista.findViewById(R.id.txtOcasion);

        txtTipo.setText(ListaPrendas.get(position).getTipo());
        txtOcasion.setText(ListaPrendas.get(position).getOcasion());
        txtTemporada.setText(ListaPrendas.get(position).getTemporada());
        Log.d("MostrarLista", "Obtiene las cosas de la lista");
        return vista;
    }

}

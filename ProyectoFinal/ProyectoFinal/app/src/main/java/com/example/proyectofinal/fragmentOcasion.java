package com.example.proyectofinal;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.proyectofinal.entidades.Adaptador;
import com.example.proyectofinal.entidades.Prenda;

import java.util.ArrayList;

public class fragmentOcasion extends Fragment {
    View rootView;
    ListView listViewPrendas;
    Adaptador miAdaptador;
    ArrayList<Prenda> listaPrendas;
    public fragmentOcasion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Ocasion", "Llega al fragment");
        rootView = inflater.inflate(R.layout.fragment_ocasion, container, false);
        Log.d("Ocasion", "Se hace lo del root view");
        ObtenerReferencias();
        listaPrendas = Prenda.ObtenerTodas(getActivity());
        if(listaPrendas != null){
            Log.d("MostrarLista", "Prenda devuelve una lista con prendas");
        }
        obtenerLista();
        return rootView;
    }

    private void ObtenerReferencias(){
        Log.d("Ocasion", "Se obtiene la referencia de la lista");
        listViewPrendas = (ListView) rootView.findViewById(R.id.lstPrendas);
    }

    public  void obtenerLista() {
        Log.d("Ocasion", "Llega a ObtenerLista");
        miAdaptador = new Adaptador(getActivity(), listaPrendas);
        Log.d("Ocasion", "Le manda la lista al adaptador");
        listViewPrendas.setAdapter(miAdaptador);
    }

}

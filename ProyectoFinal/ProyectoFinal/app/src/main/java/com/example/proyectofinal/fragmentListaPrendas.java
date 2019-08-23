package com.example.proyectofinal;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.proyectofinal.entidades.Adaptador;
import com.example.proyectofinal.entidades.Prenda;
import java.util.ArrayList;

public class fragmentListaPrendas extends Fragment {

     View rootView;
     ListView listViewPrendas;
    //ArrayList<String> listaInformacion;
     Adaptador miAdaptador;
     ArrayList<Prenda> listaPrendas;
    public fragmentListaPrendas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MostrarLista", "Llega al fragment");
        rootView = inflater.inflate(R.layout.fragment_lista, container, false);
        Log.d("MostrarLista", "Se hace lo del root view");
        ObtenerReferencias();
        listaPrendas = Prenda.ObtenerTodas(getActivity());
        if(listaPrendas != null){
            Log.d("MostrarLista", "Prenda devuelve una lista con prendas");
        }
        obtenerLista();
        return rootView;
    }

    private void ObtenerReferencias(){
        Log.d("MostrarLista", "Se obtiene la referencia de la lista");
        listViewPrendas = (ListView) rootView.findViewById(R.id.lstPrendas);
    }

    public  void obtenerLista() {
        /*listaInformacion = new ArrayList<>();
        for(int i = 0; i < listaPrendas.size(); i++) {
            listaInformacion.add(listaPrendas.get(i).getId() + listaPrendas.get(i).getOcasion() + listaPrendas.get(i).getTemporada() + listaPrendas.get(i).getTipo());
        }*/
        Log.d("MostrarLista", "Llega a ObtenerLista");
        miAdaptador = new Adaptador(getActivity(), listaPrendas);
        Log.d("MostrarLista", "Le manda la lista al adaptador");
        listViewPrendas.setAdapter(miAdaptador);
    }


}

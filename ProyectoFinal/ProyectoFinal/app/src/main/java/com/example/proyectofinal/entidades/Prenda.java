package com.example.proyectofinal.entidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.proyectofinal.ManejadorBaseDeDatos;
import com.example.proyectofinal.fragmentListaPrendas;

import java.util.ArrayList;

public class Prenda {
    int id;
    String ocasion;
    String temporada;
    String tipo;
    static ManejadorBaseDeDatos AccesoALaBase;
    static SQLiteDatabase BaseDeDatos;
    static ArrayList<Prenda> ListaADevolver;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOcasion() {
        return ocasion;
    }

    public void setOcasion(String ocasion) {
        this.ocasion = ocasion;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public Prenda(int _id, String _ocasion, String _temporada, String _tipo) {
        id = _id;
        ocasion = _ocasion;
        temporada = _temporada;
        tipo = _tipo;
    }
    public Prenda(){

    }

    public static ArrayList<Prenda> ObtenerTodas(Context context){
        Log.d("MostrarLista", "Le llega el array a la prenda");
        AccesoALaBase = new ManejadorBaseDeDatos(context,"Prendas.sqlite",null,1);
        BaseDeDatos = AccesoALaBase.getReadableDatabase();
        ListaADevolver = new ArrayList<>();
        Cursor cursor;
        String SQLLectura;
        SQLLectura="select * from Prendas";
        Log.d("MostrarLista","la consulta es:" + SQLLectura);
        cursor =AccesoALaBase.EjecutarConsulta(SQLLectura);
        if(cursor == null){
            Log.d("MostrarLista", "El cursor es null");
        }
        //REGISTROSLEIDOS SE UTIIZA COMO UN ARRAY:
        if(cursor.getCount()>0){
            for(int PunteroRegistro=0; PunteroRegistro<cursor.getCount(); PunteroRegistro++){
                Prenda prenda = new Prenda();
                cursor.moveToPosition(PunteroRegistro);
                Log.d("MostrarLista","entro a leer");

                prenda.setId(cursor.getInt(0));
                prenda.setOcasion(cursor.getString(1));
                prenda.setTemporada(cursor.getString(2));
                prenda.setTipo(cursor.getString(3));

                Log.d("MostrarLista","El id de la prenda traida es " + prenda.getId());
                Log.d("MostrarLista","El nombre de la prenda traida es " + prenda.getOcasion());

                ListaADevolver.add(prenda);
            }
        }else{
            Log.d("MostrarLista","No trae registros");
        }

       return ListaADevolver;
    }



}

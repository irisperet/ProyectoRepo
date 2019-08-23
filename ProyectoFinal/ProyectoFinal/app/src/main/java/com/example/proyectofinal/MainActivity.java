package com.example.proyectofinal;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.proyectofinal.entidades.Prenda;


public class MainActivity extends Activity {

    Button btnInsertar;
    Button btnVerLista;
    private ManejadorBaseDeDatos mbd;
    MyOpenHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbd = new ManejadorBaseDeDatos(this,"Prendas.sqlite",null, 1);
        obtenerReferencias();
        setearListeners();
    }

    public ManejadorBaseDeDatos getManejadorBaseDeDatos() {
        return mbd;
    }
    public void obtenerReferencias(){
        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnVerLista = (Button) findViewById(R.id.btnLista);
    }

    private void setearListeners() {
        btnInsertar.setOnClickListener(btnInsertar_Click);
        btnVerLista.setOnClickListener(btnVerLista_Click);
    }

    protected View.OnClickListener btnInsertar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InsertarNuevaPrenda();
        }
    };

    protected View.OnClickListener btnVerLista_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VerListadePrendas();
        }
    };

    private void InsertarNuevaPrenda(){
        Log.d("InsertarPrenda", "Le pide al fragment que venga");
        FragmentManager adminFragment;
        FragmentTransaction transacFragment;
        fragmentInsertar       elFragment1;

        elFragment1 = new fragmentInsertar();

        adminFragment   = getFragmentManager();
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.lytContenedor, elFragment1);
        transacFragment.commit();
    }

    private void VerListadePrendas(){
        FragmentManager     adminFragment;
        FragmentTransaction transacFragment;
        fragmentListaPrendas       elFragment1;

        elFragment1 = new fragmentListaPrendas();

        adminFragment   = getFragmentManager();
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.lytContenedor, elFragment1);
        Log.d("MostrarLista", "Se llena el contenedor");
        transacFragment.commit();
        Log.d("MostrarLista", "Se llama a mbd");

    }


}

package com.example.proyectofinal;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectofinal.entidades.Prenda;

import java.util.ArrayList;
import java.util.List;

public class fragmentInsertar extends Fragment {

    View        rootView;
    private static final int GALLERY_REQUEST_CODE = 1 ;
    Button btnEnviar;
    ImageView imgMostrar;
    Button btnFoto;

    ArrayList<String> ocasiones;
    ArrayAdapter<String> adaptadorOcasion;
    Spinner spnOcasion;

    ArrayList<String> temporadas;
    ArrayAdapter<String> adaptadorTemporada;
    Spinner spnTemporada;

    ArrayList<String> tipos;
    ArrayAdapter<String> adaptadorTipo;
    Spinner spnTipo;

    Prenda prenda;
    SQLiteDatabase BaseDeDatos;

    public fragmentInsertar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //aca me tengo que fijar de poner bien el nombre del layout del fragment
        rootView = inflater.inflate(R.layout.fragment_insertar, container, false);

        Log.d("InsertarPrenda", "Llega al fragment");
        //ActionBar actionBar = getSupportActionBar(); ((ActionBar) actionBar).hide();
        ocasiones = new ArrayList<>();
        ocasiones.add("Escuela");
        ocasiones.add("Trabajo");
        ocasiones.add("Fiesta");
        ocasiones.add("Boliche");
        adaptadorOcasion = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, ocasiones);
        adaptadorOcasion.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        temporadas = new ArrayList<>();
        temporadas.add("Verano");
        temporadas.add("Otoño");
        temporadas.add("Invierno");
        temporadas.add("Primavera");
        adaptadorTemporada = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, temporadas);
        adaptadorTemporada.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        tipos = new ArrayList<>();
        tipos.add("Remera");
        tipos.add("Buzo");
        tipos.add("Pantalon");
        tipos.add("Campera");
        adaptadorTipo = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, tipos);
        adaptadorTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        ObtenerReferencias();
        SetearListeners();

        //MostrarInformacion();

        return rootView;
    }

    /*private void MostrarInformacion() {

        MainActivity activityAnfitriona = (MainActivity) getActivity();

        String strNombre = activityAnfitriona.getNombre();
        tvNombre.setText(strNombre);

    }*/

    private void ObtenerReferencias() {
        btnEnviar = (Button) rootView.findViewById(R.id.btnEnviar);
        spnOcasion = (Spinner) rootView.findViewById(R.id.cmbOcasion);
        spnOcasion.setAdapter(adaptadorOcasion);
        spnTemporada = (Spinner) rootView.findViewById(R.id.cmbTemporada);
        spnTemporada.setAdapter(adaptadorTemporada);
        spnTipo = (Spinner) rootView.findViewById(R.id.cmbTipo);
        spnTipo.setAdapter(adaptadorTipo);
        imgMostrar = (ImageView) rootView.findViewById(R.id.imgMostrar);
        btnFoto = (Button) rootView.findViewById(R.id.btnFoto);
    }

    private void SetearListeners(){
        btnEnviar.setOnClickListener(btnEnviar_Click);
        btnFoto.setOnClickListener(btnFoto_Click);
    }

    View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int iOcasionSeleccionada = spnOcasion.getSelectedItemPosition();
            String strOcasionSeleccionada = spnOcasion.getItemAtPosition(iOcasionSeleccionada).toString();
            int iTemporadaSeleccionada = spnTemporada.getSelectedItemPosition();
            String strTemporadaSeleccionada = spnTemporada.getItemAtPosition(iTemporadaSeleccionada).toString();
            int iTipoSeleccionado = spnTipo.getSelectedItemPosition();
            String strTipoSeleccionado = spnTipo.getItemAtPosition(iTipoSeleccionado).toString();
            validacion(strOcasionSeleccionada, strTemporadaSeleccionada, strTipoSeleccionado);
        }
    };

    View.OnClickListener btnFoto_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pickFromGallery();
        }
    };

    /*public void insertarPrenda(String strOcasion, String strTemporada, String strTipo){
        MyOpenHelper dbHelper = new MyOpenHelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            // Insert con execSQL
            //db.execSQL("INSERT INTO comments (user, comment) VALUES ('Digital Learning','Esto es un comentario insertado usando el método execSQL()')");

            // Insert con ContentValues (esto claramente lo tengo que personalizar)
            ContentValues cv = new ContentValues();
            cv.put("ocasion",strOcasion);
            cv.put("temporada", strTemporada);
            cv.put("tipo", strTipo);
            db.insert("PRENDAS", null, cv);
            Toast msg = Toast.makeText(this.getActivity(), "Se registro correctamente", Toast.LENGTH_LONG);
            msg.show();
        }

    }*/

    public void validacion(String strOcasion, String strTemporada, String strTipo){
        if((strOcasion.equals("")) || (strTemporada.equals("")) || (strTipo.equals("")) ){
            Toast msg = Toast.makeText(this.getActivity(), "Por favor, completa todos los campos", Toast.LENGTH_LONG);
            msg.show();
        }
        else {
            prenda = new Prenda(0, strOcasion, strTemporada, strTipo);
            //tengo que castear la activity

            ManejadorBaseDeDatos mbd = ((MainActivity)getActivity()).getManejadorBaseDeDatos();
            Log.d("InsertarPrenda", "Le manda al manejador");
            BaseDeDatos = mbd.getWritableDatabase();
            mbd.insertarPrenda( strOcasion, strTemporada, strTipo, BaseDeDatos);
        }
    }

    private void pickFromGallery(){ //DESPLIEGA LAS OPCIONES PARA ELEGIR UNA IMAGEN DE LA GALERIA
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // ASEGURA QUE SOLO SE GUARDEN IMAGENES
        intent.setType("image/*");
        // EL ARRAY SE USA PARA ASEGURAR QUE SOLO SE PUEDAN SUBIR IMAGENES DE ESTOS TIPOS
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // el result code es RESULT_OK solo si el usuario selecciona una imagen
        if (resultCode == MainActivity.RESULT_OK) {
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData devuelve el contenido URI para la imagen seleccionada
                    Uri selectedImage = data.getData();
                    imgMostrar.setImageURI(selectedImage); //ESTO SETEA LA IMAGEN EN EL XML
                    break;
            }
        }
    }


}

package com.example.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ManejadorBaseDeDatos extends SQLiteOpenHelper {
    public  ManejadorBaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*private void DropTables(SQLiteDatabase db){
        Log.d("InsertarPrenda","DropTables");
        db.execSQL("DROP TABLE IF EXISTS Prendas");
    }*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase BasedeDatos) {
        Log.d("InsertarPrenda","onCreate");
        String SQLCrearTabla;
        SQLCrearTabla = "CREATE TABLE Prendas(_id INTEGER PRIMARY KEY AUTOINCREMENT, ocasion TEXT, temporada TEXT, tipo TEXT)";
        //Ejecuto la creacion  de la tabla Comidas
        BasedeDatos.execSQL(SQLCrearTabla);
        Log.d("InsertarPrenda", "Se crea la tabla");
    }

    public  void insertarPrenda(String ocasion,String temporada, String tipo, SQLiteDatabase BasedeDatos)
    {
        Log.d("InsertarPrenda", "Llega al manejador");
        ContentValues NuevoRegistro = new ContentValues();
        Log.d("InsertarPrenda", "Se crea el content values");
        NuevoRegistro.put("ocasion",ocasion.toUpperCase());
        NuevoRegistro.put("temporada",temporada.toUpperCase());
        NuevoRegistro.put("tipo",tipo.toUpperCase());
        Log.d("InsertarPrenda", "Se le meten las cosas");
        BasedeDatos.insert("Prendas",null,NuevoRegistro);
        Log.d("InsertarPrenda", "Se inserta la prenda");
    }

    public Cursor EjecutarConsulta(String Consulta)
    {
        Log.d("MostrarLista", "Llega al manejador");
        SQLiteDatabase midb;
        midb = this.getReadableDatabase();
        Cursor RegistrosLeidos;
        Log.d("MostrarLista","La consulta es: " + Consulta);
        RegistrosLeidos=midb.rawQuery(Consulta,null);
        Log.d("MostrarLista", "Se ejecuta la consulta y devuelve el array");
        return RegistrosLeidos;
    }


}

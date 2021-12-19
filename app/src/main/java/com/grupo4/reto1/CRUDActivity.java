package com.grupo4.reto1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CRUDActivity extends AppCompatActivity {

    private EditText etIdCarta, etProducto, etDescripcion, etPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        etIdCarta = (EditText) findViewById(R.id.txtGralId);
        etProducto = (EditText) findViewById(R.id.txtGralProducto);
        etDescripcion = (EditText) findViewById(R.id.txtGralDescripcion);
        etPrecio = (EditText) findViewById(R.id.txtGralPrecio);
    }

        //Método para dar de alta los productos

    //Método para dar de alta los productos
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String idCarta = etIdCarta.getText().toString();
        String producto = etProducto.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();

        if(!idCarta.isEmpty() && !producto.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("idCarta", idCarta);
            registro.put("producto", producto);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            BaseDeDatos.insert("CARTA", null, registro);

            BaseDeDatos.close();
            etIdCarta.setText("");
            etProducto.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    //Método para consultar un artículo o producto
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String idCarta = etIdCarta.getText().toString();

        if(!idCarta.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select producto, descripcion, precio from CARTA where idCarta =" + idCarta, null);

            if(fila.moveToFirst()){
                etProducto.setText(fila.getString(0));
                etDescripcion.setText(fila.getString(1));
                etPrecio.setText(fila.getString(2));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }



    //Método para eliminar un producto o Artículo
    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String idCarta = etIdCarta.getText().toString();

        if(!idCarta.isEmpty()){

            int cantidad = BaseDatabase.delete("articulos", "idCarta=" + idCarta, null);
            BaseDatabase.close();

            etIdCarta.setText("");
            etProducto.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }
    //Método para modificar un artículo o producto
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String idCarta = etIdCarta.getText().toString();
        String producto = etProducto.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();

        if(!idCarta.isEmpty() && !producto.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("idCarta", idCarta);
            registro.put("producto", producto);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = BaseDatabase.update("CARTA", registro, "idCarta=" + idCarta, null);
            BaseDatabase.close();

            if(cantidad == 1){
                Toast.makeText(this, "Artículo modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
package com.grupo4.reto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Declaración de varibles
     **/

    /**
     * Botón de Ingreso
     **/
    Button btn_Oracle;
    Button btn_Restaurantes;

    /**
     * Texto de Bienvenida
     **/
    TextView txt_Bienvenida;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Constructor texto de Bienvenida
         **/
        txt_Bienvenida = (TextView)findViewById(R.id.txt_Bienvenida);

        /**
         * Constructor botón de oracle
         **/
        btn_Oracle = (Button)findViewById(R.id.btn_Oracle);
        btn_Oracle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ListGET = new Intent(getApplicationContext(), GetActivity.class);
                startActivity(ListGET);

                txt_Bienvenida.setText("Para modificar información");
                Toast.makeText(getApplicationContext(), "Nube de Oracle", Toast.LENGTH_LONG).show();
            }
        });

        /**
         * Constructor botón de mapas
         **/
        btn_Restaurantes = (Button)findViewById(R.id.btn_Restaurantes);
        btn_Restaurantes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ListRestaurantes = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(ListRestaurantes);

                txt_Bienvenida.setText("Visítanos");
                Toast.makeText(getApplicationContext(), "Esta es la ubicación de nuestros restaurantes", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * Implementación de menú de opciones
     **/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.inicio) {
            Toast.makeText(getApplicationContext(), "Empecemos", Toast.LENGTH_LONG).show();
            Intent pantallaInicio = new Intent(this, MainActivity.class);
            startActivity(pantallaInicio);
        }
        if (id == R.id.menu){
            Toast.makeText(getApplicationContext(), "Escoge nuestras deliciosas recetas", Toast.LENGTH_LONG).show();
            Intent pantallaMenu = new Intent(this, MainActivity2.class);
            startActivity(pantallaMenu);
        }
        if (id == R.id.combos){
            Toast.makeText(getApplicationContext(), "Más disfrute al mejor precio", Toast.LENGTH_LONG).show();
            Intent pantallaCombos = new Intent(this, MainActivity3.class);
            startActivity(pantallaCombos);
        }
        if (id == R.id.CRUD){
            Toast.makeText(getApplicationContext(), "Gestión de información", Toast.LENGTH_LONG).show();
            Intent pantallaCRUD = new Intent(this, CRUDActivity.class);
            startActivity(pantallaCRUD);
        }
        if (id == R.id.restaurantes){
            Toast.makeText(getApplicationContext(), "Ven, visítanos", Toast.LENGTH_LONG).show();
            Intent pantallaRestaurantes = new Intent(this, MapActivity.class);
            startActivity(pantallaRestaurantes);
        }
        if (id == R.id.oracle){
            Toast.makeText(getApplicationContext(), "Información en la nube", Toast.LENGTH_LONG).show();
            Intent pantallaOracle = new Intent(this, GetActivity.class);
            startActivity(pantallaOracle);
        }
        return super.onOptionsItemSelected(item);
    }

}
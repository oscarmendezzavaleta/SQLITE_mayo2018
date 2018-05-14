package com.example.pcoscar.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText otxtprecio, otxtcodigo , otxtdescripcion ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        otxtcodigo=findViewById(R.id.txtcodigo);
        otxtdescripcion=findViewById(R.id.txtdescripcion);
        otxtprecio=findViewById(R.id.txtprecio);



    }

    public  void Guardar(View view){

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String Codigo =otxtcodigo.getText().toString();
        String descripcion =otxtdescripcion.getText().toString();
        String precio=otxtprecio.getText().toString();

        if (!Codigo.isEmpty() &&  !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro =new ContentValues();
            registro.put("codigo",Codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            BaseDeDatos.insert("articulos",null,registro);
            BaseDeDatos.close();

            otxtcodigo.setText("");
            otxtdescripcion.setText("");
            otxtprecio.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_SHORT).show();
        }


    }
    public  void Modificar(View view){

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String Codigo =otxtcodigo.getText().toString();
        String descripcion =otxtdescripcion.getText().toString();
        String precio=otxtprecio.getText().toString();

        if (!Codigo.isEmpty() &&  !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro =new ContentValues();
            registro.put("codigo",Codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            int cantidad=BaseDeDatos.update("articulos",registro,"codigo=" + Codigo,null);
            BaseDeDatos.close();

            otxtcodigo.setText("");
            otxtdescripcion.setText("");
            otxtprecio.setText("");

            if (cantidad==1){
                Toast.makeText(this,"Se Modifico Correctamente",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El Articulo no existe",Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_SHORT).show();
        }




    }
    public  void Consultar(View view){

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String codigo=otxtcodigo.getText().toString();

        if (!codigo.isEmpty()){
            Cursor fila=BaseDeDatos.rawQuery("Select * from articulos where codigo=" + codigo ,null);
            if (fila.moveToFirst()){
                otxtdescripcion.setText(fila.getString(1));
                otxtprecio.setText(fila.getString(2));
                BaseDeDatos.close();
            }else {
                Toast.makeText(this,"No Exite el Codigo",Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }


        }else {
            Toast.makeText(this,"Ingresa Codigo",Toast.LENGTH_SHORT).show();
        }


    }
    public  void eliminar(View view){

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String codigo=otxtcodigo.getText().toString();

        if (!codigo.isEmpty()){

            int cantidad=BaseDeDatos.delete("articulos","codigo=" + codigo,null);
            BaseDeDatos.close();
            otxtcodigo.setText("");
            otxtdescripcion.setText("");
            otxtprecio.setText("");

            if (cantidad==1){
                Toast.makeText(this,"Se Elimino Correctamente",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El Articulo no existe",Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this,"Ingresa Codigo",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.contactosdb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private myDB myyDB;
    private DataBaseHelper database ;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private LinkedList<String> mWordList;
    private int added=1;
    private int indice=12;
    private static String[] contacts =new String[100];
    private static String[] numeros =new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mWordList= new LinkedList<String>();
        crear();
        seleccionar();
        indice=0;
        for(int i = 0; i<contacts.length; i++){
            if(contacts[i]!=null){
                mWordList.add(contacts[i]+"                                 "+ numeros[i]);
                indice++;
            }
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void dialogo(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Añadir telefono");
        dialogo1.setMessage("Introduce el nombre");
        dialogo1.setCancelable(false);
        final EditText input = new EditText(this);
        dialogo1.setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo2(input.getText().toString());
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input.setHint("nombre");
        dialogo1.setView(input);
        dialogo1.show();
    }

    public void dialogo2(final String nombreContacto){
        final String nombre=nombreContacto;
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Añadir telefono");
        dialogo1.setMessage("Introduce el numero de telefono");
        dialogo1.setCancelable(false);
        final EditText input2 = new EditText(this);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar(nombre, input2.getText().toString());
                insertar(nombre, input2.getText().toString());
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        input2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
        input2.setHint("telefono");
        dialogo1.setView(input2);
        dialogo1.show();
    }

    public void insertar(String nombre, String number){
        myyDB = new myDB(this);
        myyDB.createRecords(String.valueOf(indice+added-1), nombre, number);
        Cursor cur= myyDB.selectRecords();
        cur.moveToLast();
        contacts[indice+added]=(cur.getString(1));
        numeros[indice+added]=(cur.getString(2));
        mWordList.add(contacts[indice+added]+"                                 "+ numeros[indice+added]);
        added++;
    }

    public static void borrar(String delet){
        //myDB myyDB = new myDB(this);
        //myyDB.delete(DATABASE_TABLE, KEY_ROWID + "=" + row, null);
    }

    public void aceptar(String nombre, String numero) {
        Toast t=Toast.makeText(this,"Telefono "+nombre+" - "+numero, Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        toastea(this,"insercion cancelada");
    }

    public void toastea(Context context, String tosta){
        String tostada= tosta;
        Toast toast1 =
                Toast.makeText(context, tostada, Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void crear(){
        DataBaseHelper my= new DataBaseHelper(this);
        myyDB = new myDB(this);
        String[] nombress={"Paco", "Maria", "Marta", "Marcela", "Socorro", "Carolain", "Daniel", "Eustaquio", "Pablo", "Miraflores", "Flopi"};
        String[] numeross={"929787595", "626595848", "946875485", "606258596", "902857462", "626595484", "626544788", "908756632", "903262120", "945658999", "956321236"};
        int i;
        for(i=0;i<11;i++){
            myyDB.createRecords(String.valueOf(i),nombress[i],numeross[i]);
        }
    }

    public void seleccionar(){
        int i;
        Cursor cur= myyDB.selectRecords();
        i=0;
        contacts[i]=(cur.getString(1));
        numeros[i]=(cur.getString(2));
        while(cur.moveToNext()){
            i++;
            contacts[i]=(cur.getString(1));
            numeros[i]=(cur.getString(2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void detalles(){
        startActivity(new Intent(this, pantallaDetalles.class));
    }
}

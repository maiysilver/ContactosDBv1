package com.example.contactosdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class myDB {
    private static SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public final static String TABLE="contactos"; // name of table
    public final static String ID="_id"; // id value
    public final static String NAME="name";  // name
    public final static String TELEF="telefono"; //telefono


    public myDB(Context context){
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public static long createRecords(String id, String name, String telef){
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, name);
        values.put(TELEF, telef);
        Log.e(id, name);
        return database.insert(TABLE, null, values);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {ID, NAME, TELEF};
        Cursor mCursor = database.query(true, TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public void borrar(String value){
        database.execSQL("DELETE FROM " + TABLE+ " WHERE "+NAME+"='"+value+"'");
    }
}

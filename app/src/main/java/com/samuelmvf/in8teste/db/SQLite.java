package com.samuelmvf.in8teste.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLite {

    private SQLiteDatabase banco;

    public SQLite(SQLiteDatabase bd){
        this.setBanco(bd);
    }

    public void insertData(String hist){
        try{
            String query = "INSERT INTO tbl_historic(historic) VALUES ('"+hist+"')";
            getBanco().execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void updateData(String data){
        try{
            String query = "UPDATE tbl_historic SET historic = '"+data+"' WHERE id=1";
            getBanco().execSQL(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void listData(){
        try{
            Cursor cursor = getBanco().rawQuery("SELECT * FROM tbl_historic",null);

            int indiceHistoric = cursor.getColumnIndex("historic");

            cursor.moveToFirst();
            while(cursor != null)
            {
                String id = cursor.getString(0);
                String data = cursor.getString(indiceHistoric);

                Log.i("Resultado - id",  id + " | Data: " + data);

                if(cursor.isLast())
                    cursor = null;
                else
                    cursor.moveToNext();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getData(){
        try{
            Cursor cursor = getBanco().rawQuery("SELECT * FROM tbl_historic",null);

            int indiceHistoric = cursor.getColumnIndex("historic");

            cursor.moveToFirst();
            String data = cursor.getString(indiceHistoric);

            return data;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public SQLiteDatabase getBanco() {
        return banco;
    }

    public void setBanco(SQLiteDatabase banco) {
        this.banco = banco;
    }
}

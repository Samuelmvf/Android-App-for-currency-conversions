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
    public void updateData(){
        try{
            /*String query = "UPDATE  FROM tbl_dados";
            getBanco().execSQL(query);*/

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //GETTERS AND SETTERS

    public SQLiteDatabase getBanco() {
        return banco;
    }

    public void setBanco(SQLiteDatabase banco) {
        this.banco = banco;
    }
}

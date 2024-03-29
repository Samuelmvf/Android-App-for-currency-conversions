package com.samuelmvf.in8teste.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.samuelmvf.in8teste.R;
import com.samuelmvf.in8teste.db.SQLite;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences prefs = null;
    SQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {

            SQLiteDatabase data = openOrCreateDatabase("IN8", MODE_PRIVATE,null);
            data.execSQL("CREATE TABLE IF NOT EXISTS tbl_historic (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, historic MEDIUMTEXT NOT NULL)");
            database = new SQLite(data);

            prefs = getSharedPreferences("com.samuelmvf.in8teste", MODE_PRIVATE);

            if(prefs.getBoolean("firstrun", true))
            {
                Log.i("Resultado: ","FirstTime");
                database.insertData("");
                database.listData();
            }
            else
            {
                Log.i("Resultado: ","Not FirstTime ");
                database.listData();
            }

            data.close();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 1500);
        }
        catch (Exception e ){
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

}

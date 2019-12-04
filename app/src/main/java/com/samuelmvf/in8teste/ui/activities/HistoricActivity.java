package com.samuelmvf.in8teste.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.samuelmvf.in8teste.R;
import com.samuelmvf.in8teste.db.SQLite;
import com.samuelmvf.in8teste.model.ListOfConversions;
import com.samuelmvf.in8teste.ui.Utils.Utils;
import com.samuelmvf.in8teste.ui.adapters.CustomHistoricListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoricActivity extends AppCompatActivity {

    @BindView(R.id.lvHistoric)
    ListView historic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        ButterKnife.bind(this);

        makeHistoricConversions();
    }

    @OnClick(R.id.ibBackToConvertCurrency)
    public void back(){
        super.onBackPressed();
    }

    public void makeHistoricConversions(){

        SQLiteDatabase data = openOrCreateDatabase("IN8", MODE_PRIVATE,null);
        SQLite db = new SQLite(data);
        Gson gson = new Gson();

        String strConversions = db.getData();

        if (!strConversions.isEmpty()){
            ListOfConversions listOfConversions = gson.fromJson(strConversions,ListOfConversions.class);
            CustomHistoricListView adapter = new CustomHistoricListView(HistoricActivity.this,listOfConversions);
            historic.setVisibility(View.VISIBLE);
            historic.setAdapter(adapter);
        }
        else {
            String error_message = "No conversions made!";
            AlertDialog alertDialog = Utils.errorMessage(error_message, HistoricActivity.this);
            alertDialog.show();
        }
        data.close();
    }
}

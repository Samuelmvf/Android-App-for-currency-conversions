package com.samuelmvf.in8teste.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.samuelmvf.in8teste.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ibBackToConvertCurrency)
    public void back(){
        super.onBackPressed();
    }
}

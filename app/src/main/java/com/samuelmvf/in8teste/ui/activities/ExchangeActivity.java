package com.samuelmvf.in8teste.ui.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.samuelmvf.in8teste.R;
import com.samuelmvf.in8teste.app.PanelAppApplication;
import com.samuelmvf.in8teste.db.SQLite;
import com.samuelmvf.in8teste.model.Conversion;
import com.samuelmvf.in8teste.model.Exchange;
import com.samuelmvf.in8teste.model.ListOfConversions;
import com.samuelmvf.in8teste.model.Rate;
import com.samuelmvf.in8teste.services.ServiceExchange;
import com.samuelmvf.in8teste.ui.Utils.Utils;
import com.samuelmvf.in8teste.ui.adapters.CustomListView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeActivity extends AppCompatActivity {

    @BindView(R.id.spCoins)
    Spinner coin;

    @BindView(R.id.etValue)
    EditText money;

    ProgressDialog progress;
    Exchange exchangeCoin;

    @BindView(R.id.lvCountryCurrency)
    ListView countryCurrency;

    @BindView(R.id.rlCountryCurrency)
    RelativeLayout allCurrencys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        ButterKnife.bind(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.type_coins,android.R.layout.simple_spinner_dropdown_item);
        coin.setAdapter(adapter);

        money.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }
        });


    }
    @OnClick(R.id.btConvert)
    public void convertCoin(){

        if(validateFields())
        {
            double amount = Double.parseDouble(money.getText().toString());
            String typeofCoin = coin.getSelectedItem().toString();

            progress =  new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Converting your amount..");
            progress.setCancelable(false);
            progress.show();

            ServiceExchange s = PanelAppApplication.getInstance().getServiceExchange();
            Call<Exchange> call = s.exchangebyCoin(typeofCoin);
            call.enqueue(new Callback<Exchange>() {
                @Override
                public void onResponse(Call<Exchange> call, Response<Exchange> response) {
                    if(response.code() == 200){
                        progress.dismiss();
                        try {
                            exchangeCoin = response.body();
                            CustomListView adapter = new CustomListView(ExchangeActivity.this,exchangeCoin.getRates(),amount);
                            allCurrencys.setVisibility(View.VISIBLE);
                            countryCurrency.setAdapter(adapter);

                            ArrayList<Rate> ratesForConversion = new ArrayList<>();
                            HashMap<String,Double> rates = response.body().getRates();

                            for(String key:rates.keySet())
                            {
                                try {
                                    Rate r = new Rate(key,rates.get(key));
                                    ratesForConversion.add(r);
                                }
                                catch (Exception e){
                                    Log.d("Erro", "erro ");
                                }
                            }

                            Conversion conv = new Conversion(amount,typeofCoin,ratesForConversion);

                            SharedPreferences pref = getSharedPreferences("com.samuelmvf.in8teste", MODE_PRIVATE);

                            SQLiteDatabase data = openOrCreateDatabase("IN8", MODE_PRIVATE,null);
                            SQLite db = new SQLite(data);
                            Gson gson = new Gson();

                            if(pref.getBoolean("firstrun", true))
                            {
                                ArrayList<Conversion> arrayConversion = new ArrayList<>();
                                arrayConversion.add(conv);

                                ListOfConversions listOfConversions = new ListOfConversions(arrayConversion);

                                String strConversions = gson.toJson(listOfConversions);

                                db.updateData(strConversions);

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("firstrun", false);
                                editor.apply();
                            }
                            else{
                                String strConversions = db.getData();
                                ListOfConversions listOfConversions = gson.fromJson(strConversions,ListOfConversions.class);

                                listOfConversions.getConversions().add(conv);
                                strConversions = gson.toJson(listOfConversions);

                                db.updateData(strConversions);
                            }


                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"ERRO...", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else
                    {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),"Codigo de resposta da API: "+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Exchange> call, Throwable t) {

                    progress.dismiss();
                    String error_message = "Connection Failed, please check your internet connection.";
                    AlertDialog alertDialog = Utils.errorMessage(error_message, ExchangeActivity.this);
                    alertDialog.show();
                }
            });



        }
        else
        {
            String error_message = "Enter some value..";
            AlertDialog alertDialog = Utils.errorMessage(error_message, ExchangeActivity.this);
            alertDialog.show();
        }
        money.setFocusableInTouchMode(false);
        money.setFocusable(false);
        money.setFocusableInTouchMode(true);
        money.setFocusable(true);


    }
    public boolean validateFields(){
        String money_string = money.getText().toString();
        if(money_string.length() == 0 || !Utils.isNumeric(money_string))
            return false;
        else
            return true;
    }
    @OnClick(R.id.ibBackToLogin)
    public void back(){
        super.onBackPressed();
    }
    @OnClick(R.id.ibGetHistoric)
    public void goToHistoric(){
        Intent i = new Intent(ExchangeActivity.this,HistoricActivity.class);
        startActivity(i);
    }

}

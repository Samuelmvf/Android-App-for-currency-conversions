package com.samuelmvf.in8teste.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samuelmvf.in8teste.R;
import com.samuelmvf.in8teste.model.ListOfConversions;
import com.samuelmvf.in8teste.model.Rate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class CustomHistoricListView extends BaseAdapter{

    private ListOfConversions list;
    private Activity context;

    public CustomHistoricListView(Activity context, ListOfConversions lstConversions) {

        this.context = context;
        this.list = lstConversions;
    }


    @Override
    public int getCount() {
        return list.getConversions().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        double value = list.getConversions().get(position).getAmount();
        String typeCoin = list.getConversions().get(position).getBaseCoin();
        ArrayList<Rate> rates = list.getConversions().get(position).getRates();

        Random rand = new Random();
        int number1 = rand.nextInt(33);
        int number2 = rand.nextInt(33);
        while(number2 == number1)
            number2 = rand.nextInt(33);
        int number3 = rand.nextInt(33);
        while(number3 == number1 || number3 == number2)
            number3 = rand.nextInt(33);

        Rate rate1 = new Rate(rates.get(number1).getName(),rates.get(number1).getValue());
        Rate rate2 = new Rate(rates.get(number2).getName(),rates.get(number2).getValue());
        Rate rate3 = new Rate(rates.get(number3).getName(),rates.get(number3).getValue());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.list_hist, null);

        TextView currency = v.findViewById(R.id.tvlisthistCoin);
        TextView amount = v.findViewById(R.id.tvlisthistValue);
        TextView rate1Coin = v.findViewById(R.id.tvRate1Coin);
        TextView rate1amount = v.findViewById(R.id.tvRate1Amount);
        TextView rate2Coin = v.findViewById(R.id.tvRate2Coin);
        TextView rate2amount = v.findViewById(R.id.tvRate2Amount);
        TextView rate3Coin = v.findViewById(R.id.tvRate3Coin);
        TextView rate3amount = v.findViewById(R.id.tvRate3Amount);

        currency.setText(typeCoin);
        amount.setText(String.format("%.2f", value));
        rate1Coin.setText(rate1.getName());
        rate1amount.setText(String.format("%.2f", rate1.getValue()));
        rate2Coin.setText(rate2.getName());
        rate2amount.setText(String.format("%.2f", rate2.getValue()));
        rate3Coin.setText(rate3.getName());
        rate3amount.setText(String.format("%.2f", rate3.getValue()));

        return v;
    }
}


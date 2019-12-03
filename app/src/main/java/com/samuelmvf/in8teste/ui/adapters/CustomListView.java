package com.samuelmvf.in8teste.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samuelmvf.in8teste.R;
import com.samuelmvf.in8teste.model.Rate;

import java.text.DecimalFormat;
import java.util.HashMap;


public class CustomListView extends BaseAdapter{

    private HashMap list;
    private double initial_amount;
    private static final String[] keys = {"CAD","HKD","ISK","PHP","DKK","HUF","CZK","GBP","RON","SEK","IDR","INR","BRL","RUB","HRK","JPY","THB","CHF","EUR","MYR","BGN","TRY","CNY","NOK","NZD","ZAR","USD","MXN","SGD","AUD","ILS","KRW","PLN"};
    private Activity context;

    public CustomListView(Activity context, HashMap listItens, double initial_amount) {

        this.context = context;
        this.list = listItens;
        this.initial_amount = initial_amount;
    }


    @Override
    public int getCount() {
        return list.size();
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
        double value = (double) list.get(keys[position]);
        double final_value = value * initial_amount;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        final_value = Double.valueOf(decimalFormat.format(final_value));

        Rate r = new Rate(keys[position],final_value);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.list_item, null);

        TextView currency = v.findViewById(R.id.tvListItemCurrency);
        TextView amount = v.findViewById(R.id.tvListItemValue);

        currency.setText(r.getName());
        amount.setText(r.getValue()+"");

        return v;
    }
}


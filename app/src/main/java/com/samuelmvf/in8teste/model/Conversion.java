package com.samuelmvf.in8teste.model;

import java.util.ArrayList;

public class Conversion {

    private double amount;
    private String baseCoin;
    private ArrayList<Rate> rates;

    public Conversion(double amount, String baseCoin, ArrayList<Rate> rates) {
        this.amount = amount;
        this.baseCoin = baseCoin;
        this.rates = rates;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBaseCoin() {
        return baseCoin;
    }

    public void setBaseCoin(String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }
}

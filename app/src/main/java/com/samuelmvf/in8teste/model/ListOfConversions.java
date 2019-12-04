package com.samuelmvf.in8teste.model;

import java.util.ArrayList;

public class ListOfConversions {

    private ArrayList<Conversion> conversions;

    public ListOfConversions(ArrayList<Conversion> conversions) {
        this.conversions = conversions;
    }

    public ArrayList<Conversion> getConversions() {
        return conversions;
    }
}

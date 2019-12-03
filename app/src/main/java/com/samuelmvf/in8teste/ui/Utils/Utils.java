package com.samuelmvf.in8teste.ui.Utils;

import android.app.AlertDialog;
import android.content.Context;

public class Utils {
    public static AlertDialog errorMessage(String message, Context c){

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(message);
        builder.setTitle("Hey!");
        builder.setCancelable(false);
        builder.setPositiveButton("Dismiss",null);

        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
    public static boolean isNumeric (String s) {
        try {
            Double.parseDouble (s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}

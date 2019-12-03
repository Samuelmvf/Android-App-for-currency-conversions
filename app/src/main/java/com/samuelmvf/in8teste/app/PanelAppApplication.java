package com.samuelmvf.in8teste.app;

import android.app.Application;

import com.samuelmvf.in8teste.services.RestService;
import com.samuelmvf.in8teste.services.ServiceExchange;

public class PanelAppApplication extends Application {

    public static final String URL = "https://api.exchangeratesapi.io/";

    private static PanelAppApplication instance;

    private ServiceExchange serviceExchange;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createServices();
    }



    private void createServices(){
        serviceExchange = (new RestService(URL)).getService(ServiceExchange.class);
    }

    public static PanelAppApplication getInstance() {
        return instance;
    }

    public ServiceExchange getServiceExchange() {
        return serviceExchange;
    }

}

package com.samuelmvf.in8teste.services;

import com.samuelmvf.in8teste.model.Exchange;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceExchange {

    @GET("latest")
    Call<Exchange> exchangebyCoin(@Query("base") String coin);

}

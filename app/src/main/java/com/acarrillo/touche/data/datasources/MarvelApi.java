package com.acarrillo.touche.data.datasources;

import com.acarrillo.touche.data.datasources.responses.ComicsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {
    @GET("v1/public/comics")
    Call<ComicsListResponse> getComics(
            @Query("apikey") String key
    );
}

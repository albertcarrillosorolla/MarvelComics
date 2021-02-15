package com.acarrillo.touche.data.datasources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelApiFactory {
    public static final String BASE_URL = "https://gateway.marvel.com/";
    public static final String API_KEY = "731624514ba14c5006c262d76d4c474b";

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MarvelApi mMarvelApi = retrofit.create(MarvelApi.class);

    public static MarvelApi getMarvelApi(){
        return mMarvelApi;
    }
}

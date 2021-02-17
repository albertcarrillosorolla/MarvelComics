package com.acarrillo.touche.data.remotesources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelApiFactory {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(MarvelApiConsts.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MarvelApi mMarvelApi = retrofit.create(MarvelApi.class);

    public static MarvelApi getMarvelApi(){
        return mMarvelApi;
    }
}

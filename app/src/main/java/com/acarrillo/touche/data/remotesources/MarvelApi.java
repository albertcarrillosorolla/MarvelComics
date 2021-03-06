package com.acarrillo.touche.data.remotesources;

import com.acarrillo.touche.data.remotesources.responses.CharactersListResponse;
import com.acarrillo.touche.data.remotesources.responses.ComicsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {
    @GET("v1/public/comics")
    Call<ComicsListResponse> getComics(
            @Query("ts") String timestamp,
            @Query("apikey") String key,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int numItems
    );

    @GET("v1/public/characters")
    Call<CharactersListResponse> getCharactersByComicId(
            @Query("ts") String timestamp,
            @Query("apikey") String key,
            @Query("hash") String hash,
            @Query("comics") int comicId
    );
}

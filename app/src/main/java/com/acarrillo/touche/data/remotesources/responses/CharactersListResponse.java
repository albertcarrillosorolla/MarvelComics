package com.acarrillo.touche.data.remotesources.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharactersListResponse {

    @SerializedName("data")
    @Expose()
    private CharactersListResponse.CharactersListResponseData data;

    public List<CharactersListResponse.Character> getCharacters() {
        return data.characters;
    }

    static class CharactersListResponseData {
        @SerializedName("count")
        @Expose()
        public int count;

        @SerializedName("results")
        @Expose()
        public List<CharactersListResponse.Character> characters;
    }

    public static class Character{
        @SerializedName("name")
        @Expose()
        public String name;

        @SerializedName("thumbnail")
        @Expose()
        public Image image;
    }

    public static class Image{
        @SerializedName("path")
        @Expose()
        public String path;

        @SerializedName("extension")
        @Expose()
        public String extension;
    }

}

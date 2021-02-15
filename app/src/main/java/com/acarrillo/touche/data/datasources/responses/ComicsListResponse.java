package com.acarrillo.touche.data.datasources.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicsListResponse {
    @SerializedName("code")
    @Expose()
    private int code;

    @SerializedName("data")
    @Expose()
    private ComicsListResponseData data;

    public int getCount() {
        return data.count;
    }

    public List<Comic> getComics() {
        return data.comics;
    }

    public int getCode()
    {
        return code;
    }

    class ComicsListResponseData {
        @SerializedName("count")
        @Expose()
        public int count;

        @SerializedName("results")
        @Expose()
        public List<Comic> comics;
    }
    class Comic
    {
        @SerializedName("id")
        @Expose()
        String id;

        @SerializedName("title")
        @Expose()
        String title;
    }
}

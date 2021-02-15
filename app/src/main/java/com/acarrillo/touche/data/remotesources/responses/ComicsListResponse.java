package com.acarrillo.touche.data.remotesources.responses;

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

    public class ComicsListResponseData {
        @SerializedName("count")
        @Expose()
        public int count;

        @SerializedName("results")
        @Expose()
        public List<Comic> comics;
    }
    public class Comic
    {
        @SerializedName("id")
        @Expose()
        public int id;

        @SerializedName("title")
        @Expose()
        public String title;

        @SerializedName("images")
        @Expose()
        public List<Image> images;
    }
    public class Image
    {
        @SerializedName("path")
        @Expose
        public String path;

        @SerializedName("extension")
        @Expose
        public String extension;
    }
}

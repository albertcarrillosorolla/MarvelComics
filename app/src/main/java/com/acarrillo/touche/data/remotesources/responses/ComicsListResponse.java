package com.acarrillo.touche.data.remotesources.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicsListResponse {

    @SerializedName("data")
    @Expose()
    private ComicsListResponseData data;

    public List<Comic> getComics() {
        return data.comics;
    }

    static class ComicsListResponseData {
        @SerializedName("count")
        @Expose()
        public int count;

        @SerializedName("results")
        @Expose()
        public List<Comic> comics;
    }

    public static class Comic
    {
        @SerializedName("id")
        @Expose()
        public int id;

        @SerializedName("title")
        @Expose()
        public String title;

        @SerializedName("description")
        @Expose()
        public String description;

        @SerializedName("images")
        @Expose()
        public List<Image> images;
    }

    public static class Image
    {
        @SerializedName("path")
        @Expose
        public String path;

        @SerializedName("extension")
        @Expose
        public String extension;
    }
}

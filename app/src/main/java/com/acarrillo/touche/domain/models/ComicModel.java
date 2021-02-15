package com.acarrillo.touche.domain.models;

import android.os.Parcel;

import java.util.List;

public class ComicModel implements Model {

    private int mId;
    private String mTitle;

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImage(int i) {
        if(mImages.size()<=i) return "";
        return mImages.get(i);
    }

    List<String> mImages;

    public ComicModel(int id, String title, List<String> images)
    {
        mId = id;
        mTitle = title;
        mImages = images;
    }

    protected ComicModel(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mImages = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeStringList(mImages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ComicModel> CREATOR = new Creator<ComicModel>() {
        @Override
        public ComicModel createFromParcel(Parcel in) {
            return new ComicModel(in);
        }

        @Override
        public ComicModel[] newArray(int size) {
            return new ComicModel[size];
        }
    };
}

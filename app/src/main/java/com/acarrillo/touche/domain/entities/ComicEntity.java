package com.acarrillo.touche.domain.entities;

import android.os.Parcel;

import java.util.List;

public class ComicEntity implements Entity {

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

    public int getNumImages()
    {
        return mImages == null ? 0 : mImages.size();
    }

    List<String> mImages;

    public ComicEntity(int id, String title, List<String> images)
    {
        mId = id;
        mTitle = title;
        mImages = images;
    }

    protected ComicEntity(Parcel in) {
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

    public static final Creator<ComicEntity> CREATOR = new Creator<ComicEntity>() {
        @Override
        public ComicEntity createFromParcel(Parcel in) {
            return new ComicEntity(in);
        }

        @Override
        public ComicEntity[] newArray(int size) {
            return new ComicEntity[size];
        }
    };
}

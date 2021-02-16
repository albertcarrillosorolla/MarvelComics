package com.acarrillo.touche.domain.entities;

import android.os.Parcel;

public class ComicEntity implements Entity {

    private int mId;
    private String mTitle;
    private String mDescription;
    private String mImage;

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImage() {
        return mImage;
    }

    public String getDescription(){
        return mDescription;
    }

    public ComicEntity(int id, String title, String description, String image)
    {
        mId = id;
        mTitle = title;
        mDescription = description;
        mImage = image;
    }

    protected ComicEntity(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mDescription = in.readString();
        mImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mImage);
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

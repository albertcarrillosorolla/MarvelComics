package com.acarrillo.touche.domain.entities;

import android.os.Parcel;

public class CharacterEntity implements Entity {

    String mName;
    String mImage;

    public CharacterEntity(String name, String image){
        mName = name;
        mImage = image;
    }

    public String getName(){
        return mName;
    }

    public String getImage(){
        return mImage;
    }

    protected CharacterEntity(Parcel in) {
        mName = in.readString();
        mImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CharacterEntity> CREATOR = new Creator<CharacterEntity>() {
        @Override
        public CharacterEntity createFromParcel(Parcel in) {
            return new CharacterEntity(in);
        }

        @Override
        public CharacterEntity[] newArray(int size) {
            return new CharacterEntity[size];
        }
    };
}

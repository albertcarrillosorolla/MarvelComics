package com.acarrillo.touche.domain.entities;

import android.os.Parcel;

public class CharacterEntity implements Entity {

    String image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

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

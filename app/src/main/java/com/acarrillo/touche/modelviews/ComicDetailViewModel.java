package com.acarrillo.touche.modelviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acarrillo.touche.domain.entities.ComicEntity;

public class ComicDetailViewModel extends ViewModel {
    MutableLiveData<ComicEntity> mComic;

    public ComicDetailViewModel() {
        mComic = new MutableLiveData<>();
    }

    public void setComic(ComicEntity comic) {
        mComic.postValue(comic);
    }

    public MutableLiveData<ComicEntity> getComic() {
        return mComic;
    }
}

package com.acarrillo.touche.modelviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acarrillo.touche.data.ComicRepositoryImpl;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.usecases.GetComicsListUseCase;
import com.acarrillo.touche.domain.usecases.UseCaseFactory;

import java.util.ArrayList;
import java.util.List;

public class ComicsListViewModel extends ViewModel {
    public final static int ItemsPerPage = 20;

    GetComicsListUseCase mGetComicsListUseCase;

    MutableLiveData<List<ComicEntity>> mComicsList;
    MutableLiveData<Error> mError;
    MutableLiveData<Integer> mExpandedComicItemPosition;

    public ComicsListViewModel()
    {
        //This should be injected by dagger
        mGetComicsListUseCase = new UseCaseFactory().getComicsListUseCase(new ComicRepositoryImpl());

        mComicsList = new MutableLiveData<>();
        mExpandedComicItemPosition = new MutableLiveData<>();
        mError = new MutableLiveData<>();
    }

    public LiveData<List<ComicEntity>> getComics()
    {
        return mComicsList;
    }

    public LiveData<Integer> getExpandedItemPosition()
    {
        return mExpandedComicItemPosition;
    }

    public LiveData<Error> getErrors() { return mError; }

    public void loadMoreComics() {
        int offset = 0;
        if(mComicsList.getValue() != null) offset = mComicsList.getValue().size();

        mGetComicsListUseCase.execute(
            (result) -> result.apply(
                (error) -> mError.setValue(error),
                (response) -> {
                    List<ComicEntity> currentComics = (mComicsList.getValue() == null) ? new ArrayList<>() : mComicsList.getValue();
                    currentComics.addAll(response);
                    mComicsList.setValue(currentComics);
                }
            ), new GetComicsListUseCase.Params(offset, ItemsPerPage));
    }

    public void selectExpandedItem(int position) {
        mExpandedComicItemPosition.setValue(position);
    }
}

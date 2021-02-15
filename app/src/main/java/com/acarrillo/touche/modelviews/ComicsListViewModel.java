package com.acarrillo.touche.modelviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acarrillo.touche.data.ComicRepositoryImpl;
import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.usecases.GetComicsListUseCase;

import java.util.List;

public class ComicsListViewModel extends ViewModel {

    MutableLiveData<Either<Error, List<ComicModel>>> mComicsList;
    MutableLiveData<Integer> mExpandedComicItemId;
    GetComicsListUseCase mGetComicsListUseCase;

    public ComicsListViewModel()
    {
        //TODO: This should be injected by dagger
        mGetComicsListUseCase = new GetComicsListUseCase(new ComicRepositoryImpl());
        mComicsList = new MutableLiveData<>();
        mExpandedComicItemId = new MutableLiveData<>();
    }

    public LiveData<Either<Error,List<ComicModel>>> getComics()
    {
        return mComicsList;
    }

    public LiveData<Integer> getExpandedItemId()
    {
        return mExpandedComicItemId;
    }

    public void loadComics()
    {
        mGetComicsListUseCase.execute(
                response -> mComicsList.setValue(response), null);
    }
    public void selectExpandedItem(int id)
    {
        mExpandedComicItemId.setValue(id);
    }
}

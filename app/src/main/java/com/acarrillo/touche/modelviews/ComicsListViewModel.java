package com.acarrillo.touche.modelviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acarrillo.touche.data.ComicRepositoryImpl;
import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.usecases.GetComicsListUseCase;

import java.util.List;
import java.util.Observer;

public class ComicsListViewModel extends ViewModel {

    MutableLiveData<Either<Error, List<ComicModel>>> mComicsList;
    GetComicsListUseCase mGetComicsListUseCase;

    public ComicsListViewModel()
    {
        //TODO: This should be injected by dagger
        mGetComicsListUseCase = new GetComicsListUseCase(new ComicRepositoryImpl());
        mComicsList = new MutableLiveData<>();
    }

    public LiveData<Either<Error,List<ComicModel>>> getComics()
    {
        mGetComicsListUseCase.execute(
                response -> mComicsList.setValue(response), null);
        return mComicsList;
    }
}

package com.acarrillo.touche.modelviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acarrillo.touche.data.CharacterRepositoryImpl;
import com.acarrillo.touche.domain.entities.CharacterEntity;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.usecases.GetCharactersByComicIdUseCase;
import com.acarrillo.touche.domain.usecases.UseCaseFactory;

import java.util.List;

public class ComicDetailViewModel extends ViewModel {
    MutableLiveData<ComicEntity> mComic;
    MutableLiveData<List<CharacterEntity>> mCharacters;
    MutableLiveData<Error> mError;

    GetCharactersByComicIdUseCase mGetCharactersByComicIdUseCase;

    public ComicDetailViewModel() {
        //TODO: This should be injected by Dagger
        mGetCharactersByComicIdUseCase = new UseCaseFactory().getCharactersByComicIdUseCase(new CharacterRepositoryImpl());

        mComic = new MutableLiveData<>();
        mError = new MutableLiveData<>();
        mCharacters = new MutableLiveData<>();
    }

    public void loadCharacters(int comicId) {
        mGetCharactersByComicIdUseCase.execute(
                (result) ->  result.apply(
                    (error) -> mError.postValue(error),
                    (response) -> mCharacters.postValue(response)
                ),
                new GetCharactersByComicIdUseCase.Params(comicId));
    }

    public void setComic(ComicEntity comic) {
        mComic.postValue(comic);
        loadCharacters(comic.getId());
    }

    public MutableLiveData<ComicEntity> getComic() {
        return mComic;
    }
    public MutableLiveData<List<CharacterEntity>> getCharacters() {
        return mCharacters;
    }
    public MutableLiveData<Error> getError() {
        return mError;
    }
}

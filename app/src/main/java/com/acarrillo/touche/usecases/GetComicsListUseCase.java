package com.acarrillo.touche.usecases;

import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.repositories.ComicRepository;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class GetComicsListUseCase implements UseCase<List<ComicModel>, Void> {
    ComicRepository mComicRepository;

    public GetComicsListUseCase(ComicRepository comicRepository)
    {
        //This should be injected by dagger
        mComicRepository = comicRepository;
    }

    @Override
    public void execute(ResponseHandler<List<ComicModel>> handler, Void params) {
        mComicRepository.getComics(handler);
    }
}

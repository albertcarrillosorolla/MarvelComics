package com.acarrillo.touche.domain.usecases;

import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.repositories.ComicRepository;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class GetComicsListUseCase implements UseCase<List<ComicEntity>, GetComicsListUseCase.Params> {
    ComicRepository mComicRepository;

    public GetComicsListUseCase(ComicRepository comicRepository)
    {
        //TODO: This should be injected by dagger
        mComicRepository = comicRepository;
    }

    @Override
    public void execute(ResponseHandler<List<ComicEntity>> handler, Params params) {
        mComicRepository.getComics(handler, params.getOffset(), params.getNumberOfItems());
    }

    public static class Params
    {

        int mOffset;
        int mNumberOfItems;

        public Params(int offset, int numberOfItems) {
            mOffset = offset;
            mNumberOfItems = numberOfItems;
        }

        int getOffset() {
            return mOffset;
        }

        int getNumberOfItems() {
            return mNumberOfItems;
        }
    }
}

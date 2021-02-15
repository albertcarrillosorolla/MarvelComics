package com.acarrillo.touche.data;

import com.acarrillo.touche.data.datasources.LocalComicDataSource;
import com.acarrillo.touche.data.datasources.RemoteComicDataSource;
import com.acarrillo.touche.data.exceptions.NotImplementedError;
import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.repositories.ComicRepository;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComicRepositoryImpl implements ComicRepository {
    CachePolicy mCachePolicy;
    LocalComicDataSource mLocalComicDataSource;
    RemoteComicDataSource mRemoteComicDataSource;

    public ComicRepositoryImpl()
    {
        mLocalComicDataSource = new LocalComicDataSource();
        mRemoteComicDataSource = new RemoteComicDataSource();
    }

    @Override
    public void getComics(ResponseHandler<List<ComicModel>> handler) {
        if(mCachePolicy == CachePolicy.ALWAYS)
        {
            handler.handle(Either.left(new NotImplementedError()));
            return;
        }

        List<ComicModel> comics = new ArrayList<>();
        comics.add(new ComicModel(11298734, "Comic one", Arrays.asList("image.jpg", "image2.jpg")));
        handler.handle(Either.right(comics));
    }

    @Override
    public void setCachePolicy(CachePolicy cachePolicy) {
        mCachePolicy = cachePolicy;
    }

}

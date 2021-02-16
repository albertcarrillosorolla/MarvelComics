package com.acarrillo.touche.data;

import com.acarrillo.touche.data.localsources.LocalComicDataSource;
import com.acarrillo.touche.data.remotesources.RemoteComicDataSource;
import com.acarrillo.touche.data.exceptions.NotImplementedError;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.repositories.ComicRepository;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class ComicRepositoryImpl implements ComicRepository {
    CachePolicy mCachePolicy = CachePolicy.NEVER;
    LocalComicDataSource mLocalComicDataSource;
    RemoteComicDataSource mRemoteComicDataSource;

    public ComicRepositoryImpl()
    {
        mLocalComicDataSource = new LocalComicDataSource();
        mRemoteComicDataSource = new RemoteComicDataSource();
    }

    @Override
    public void getComics(ResponseHandler<List<ComicEntity>> handler, int offset, int numItems) {
        if(mCachePolicy == CachePolicy.ALWAYS)
        {
            handler.handle(Either.left(new NotImplementedError()));
            return;
        }
        mRemoteComicDataSource.getData(handler, new RemoteComicDataSource.QueryParams(offset, numItems));
    }

    @Override
    public void setCachePolicy(CachePolicy cachePolicy) {
        mCachePolicy = cachePolicy;
    }

}

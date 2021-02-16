package com.acarrillo.touche.data;

import com.acarrillo.touche.data.exceptions.NotImplementedError;
import com.acarrillo.touche.data.remotesources.RemoteCharacterDataSource;
import com.acarrillo.touche.domain.entities.CharacterEntity;
import com.acarrillo.touche.domain.repositories.CharacterRepository;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class CharacterRepositoryImpl implements CharacterRepository {
    CachePolicy mCachePolicy = CachePolicy.NEVER;
    RemoteCharacterDataSource mRemoteCharacterDataSource;

    public CharacterRepositoryImpl() {
        mRemoteCharacterDataSource = new RemoteCharacterDataSource();
    }

    @Override
    public void getCharactersByComicId(ResponseHandler<List<CharacterEntity>> handler, int comicId) {
        if(mCachePolicy == CachePolicy.ALWAYS)
        {
            handler.handle(Either.left(new NotImplementedError()));
            return;
        }
        mRemoteCharacterDataSource.getData(handler, new RemoteCharacterDataSource.QueryParams(comicId));
    }

    @Override
    public void setCachePolicy(CachePolicy cachePolicy) {
        mCachePolicy = cachePolicy;
    }
}

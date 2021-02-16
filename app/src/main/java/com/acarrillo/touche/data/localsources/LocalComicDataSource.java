package com.acarrillo.touche.data.localsources;

import com.acarrillo.touche.data.DataSource;
import com.acarrillo.touche.data.exceptions.NotImplementedError;
import com.acarrillo.touche.data.remotesources.RemoteComicDataSource;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.usecases.GetComicsListUseCase;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class LocalComicDataSource implements DataSource<List<ComicEntity>, Void> {

    @Override
    public void getData(ResponseHandler<List<ComicEntity>> handler, Void aVoid) {
        handler.handle(Either.left(new NotImplementedError()));
    }
}
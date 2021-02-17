package com.acarrillo.touche.data.localsources;

import com.acarrillo.touche.data.DataSource;
import com.acarrillo.touche.data.exceptions.NotImplementedError;
import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class LocalComicDataSource implements DataSource<List<ComicModel>> {

    @Override
    public void getData(ResponseHandler<List<ComicModel>> handler) {
        handler.handle(Either.left(new NotImplementedError()));
    }
}
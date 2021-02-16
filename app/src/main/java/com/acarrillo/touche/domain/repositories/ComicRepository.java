package com.acarrillo.touche.domain.repositories;

import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public interface ComicRepository extends Repository {
    void getComics(ResponseHandler<List<ComicEntity>> handler, int offset, int numItems);
}

package com.acarrillo.touche.domain.repositories;

import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public interface ComicRepository extends Repository {
    void getComics(ResponseHandler<List<ComicModel>> handler);
}

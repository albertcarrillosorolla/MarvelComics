package com.acarrillo.touche.domain.repositories;

import com.acarrillo.touche.domain.entities.CharacterEntity;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public interface CharacterRepository extends Repository {
    void getCharactersByComicId(ResponseHandler<List<CharacterEntity>> handler, int comicId);
}

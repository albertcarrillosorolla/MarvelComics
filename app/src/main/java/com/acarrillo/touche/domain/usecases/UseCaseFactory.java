package com.acarrillo.touche.domain.usecases;

import com.acarrillo.touche.domain.repositories.CharacterRepository;
import com.acarrillo.touche.domain.repositories.ComicRepository;

public class UseCaseFactory {
    public GetComicsListUseCase getComicsListUseCase(ComicRepository comicRepository)
    {
        return new GetComicsListUseCase(comicRepository);
    }

    public GetCharactersByComicIdUseCase getCharactersByComicIdUseCase(CharacterRepository characterRepository) {
        return new GetCharactersByComicIdUseCase(characterRepository);
    }
}

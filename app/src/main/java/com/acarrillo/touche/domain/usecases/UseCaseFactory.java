package com.acarrillo.touche.domain.usecases;

import com.acarrillo.touche.domain.repositories.CharacterRepository;
import com.acarrillo.touche.domain.repositories.ComicRepository;

public class UseCaseFactory {
    public GetComicsListUseCase getComicsListUseCase(ComicRepository comicRepository) {
        //TODO: This should be injected by Dagger
        return new GetComicsListUseCase(comicRepository);
    }

    public GetCharactersByComicIdUseCase getCharactersByComicIdUseCase(CharacterRepository characterRepository) {
        //TODO: This should be injected by Dagger
        return new GetCharactersByComicIdUseCase(characterRepository);
    }
}

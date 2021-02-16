package com.acarrillo.touche.domain.usecases;

import com.acarrillo.touche.domain.entities.CharacterEntity;
import com.acarrillo.touche.domain.repositories.CharacterRepository;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.List;

public class GetCharactersByComicIdUseCase implements UseCase<List<CharacterEntity>, GetCharactersByComicIdUseCase.Params>{

    CharacterRepository mCharacterRepository;

    public GetCharactersByComicIdUseCase(CharacterRepository characterRepository){
        mCharacterRepository = characterRepository;
    }

    @Override
    public void execute(ResponseHandler<List<CharacterEntity>> handler, Params params) {
        mCharacterRepository.getCharactersByComicId(handler, params.mComicId);
    }

    public static final class Params {
        int mComicId;

        public Params(int comicId) {
            mComicId = comicId;
        }
    }
}

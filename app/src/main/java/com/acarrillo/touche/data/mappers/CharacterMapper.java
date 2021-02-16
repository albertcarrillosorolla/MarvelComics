package com.acarrillo.touche.data.mappers;

import com.acarrillo.touche.data.remotesources.responses.CharactersListResponse;
import com.acarrillo.touche.domain.entities.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {
    public List<CharacterEntity> getCharacterEntityListFromResponse(CharactersListResponse charactersListResponse)
    {
        List<CharacterEntity> characterEntities = new ArrayList<CharacterEntity>();
        for (CharactersListResponse.Character c: charactersListResponse.getCharacters()) {
            String imagePath = "";
            if(c.image!=null)
                imagePath = c.image.path + "." + c.image.extension;
            characterEntities.add(new CharacterEntity(c.name, imagePath));
        }
        return characterEntities;
    }
}

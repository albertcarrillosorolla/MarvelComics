package com.acarrillo.touche.data.mappers;

import com.acarrillo.touche.data.remotesources.responses.ComicsListResponse;
import com.acarrillo.touche.domain.entities.ComicEntity;

import java.util.ArrayList;
import java.util.List;

public class ComicMapper {
    public List<ComicEntity> getComicsListEntityFromResponse(ComicsListResponse comicsListResponse)
    {
        List<ComicEntity> comicList = new ArrayList<ComicEntity>();
        for (ComicsListResponse.Comic c: comicsListResponse.getComics()) {
            String imagePath = "";
            if(c.images!=null && c.images.size()>0)
                imagePath = c.images.get(0).path + "." + c.images.get(0).extension;
            comicList.add(new ComicEntity(c.id, c.title, c.description, imagePath));
        }
        return comicList;
    }
}

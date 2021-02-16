package com.acarrillo.touche.data.mappers;

import com.acarrillo.touche.data.remotesources.responses.ComicsListResponse;
import com.acarrillo.touche.domain.entities.ComicEntity;

import java.util.ArrayList;
import java.util.List;

public class ComicMapper {
    public List<ComicEntity> getComicsListModelFromResponse(ComicsListResponse comicsListResponse)
    {
        List<ComicEntity> comicList = new ArrayList<ComicEntity>();
        for (ComicsListResponse.Comic c: comicsListResponse.getComics()) {
            ArrayList<String> images = new ArrayList<String>();
            for(ComicsListResponse.Image image: c.images){
                images.add(image.path+"."+image.extension);
            }
            comicList.add(new ComicEntity(c.id, c.title, images));
        }
        return comicList;
    }
}

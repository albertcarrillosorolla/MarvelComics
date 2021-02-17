package com.acarrillo.touche.data.mappers;

import com.acarrillo.touche.data.remotesources.responses.ComicsListResponse;
import com.acarrillo.touche.domain.models.ComicModel;

import java.util.ArrayList;
import java.util.List;

public class ComicMapper {
    public List<ComicModel> getComicsListModelFromResponse(ComicsListResponse comicsListResponse)
    {
        List<ComicModel> comicList = new ArrayList<ComicModel>();
        for (ComicsListResponse.Comic c: comicsListResponse.getComics()) {
            comicList.add(new ComicModel(c.id, c.title, null));
        }
        return comicList;
    }
}
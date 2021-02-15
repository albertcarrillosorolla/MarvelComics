package com.acarrillo.touche.data.datasources;

import com.acarrillo.touche.domain.models.ComicModel;

import java.util.List;

public class LocalComicDataSource implements DataSource<List<ComicModel>> {
    @Override
    public List<ComicModel> getData() {
        throw new UnsupportedOperationException();
    }
}
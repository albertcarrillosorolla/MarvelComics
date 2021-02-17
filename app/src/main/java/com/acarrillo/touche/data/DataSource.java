package com.acarrillo.touche.data;

import com.acarrillo.touche.domain.utils.ResponseHandler;

public interface DataSource<T> {
    void getData(ResponseHandler<T> handler);
}

package com.acarrillo.touche.usecases;

import com.acarrillo.touche.domain.utils.ResponseHandler;

public interface UseCase<RESULT, PARAMS> {
    void execute(ResponseHandler<RESULT> handler, PARAMS params);
}

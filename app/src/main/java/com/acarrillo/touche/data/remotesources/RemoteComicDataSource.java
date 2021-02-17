package com.acarrillo.touche.data.remotesources;

import com.acarrillo.touche.data.DataSource;
import com.acarrillo.touche.data.mappers.ComicMapper;
import com.acarrillo.touche.data.remotesources.responses.ComicsListResponse;
import com.acarrillo.touche.data.exceptions.ApiError;
import com.acarrillo.touche.data.utils.HashUtil;
import com.acarrillo.touche.domain.models.ComicModel;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteComicDataSource implements DataSource<List<ComicModel>> {

    ComicMapper mComicMapper;

    public RemoteComicDataSource()
    {
        //This should be injected by dagger
        mComicMapper = new ComicMapper();
    }

    @Override
    public void getData(ResponseHandler<List<ComicModel>> handler) {

        String ts = ""+new Date().getTime();
        String apikey = MarvelApiConsts.API_KEY;
        String hash = HashUtil.md5(ts + MarvelApiConsts.PRIVATE_KEY + MarvelApiConsts.API_KEY);

        Call<ComicsListResponse> call = MarvelApiFactory.getMarvelApi().getComics( ts, apikey, hash );

        call.enqueue(new Callback<ComicsListResponse>() {
            @Override
            public void onResponse(Call<ComicsListResponse> call, Response<ComicsListResponse> response) {
                if(response.code()!=200)
                    handler.handle(Either.left(new ApiError("Error: "+response.code()+". "+response.message())));
                else
                    handler.handle(Either.right(mComicMapper.getComicsListModelFromResponse(response.body())));
            }

            @Override
            public void onFailure(Call<ComicsListResponse> call, Throwable t) {
                handler.handle(Either.left(new ApiError(t.getMessage())));
            }
        });
    }
}

package com.acarrillo.touche.data.remotesources;

import com.acarrillo.touche.data.DataSource;
import com.acarrillo.touche.data.mappers.ComicMapper;
import com.acarrillo.touche.data.remotesources.responses.ComicsListResponse;
import com.acarrillo.touche.data.exceptions.ApiError;
import com.acarrillo.touche.data.utils.HashUtil;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteComicDataSource implements DataSource<List<ComicEntity>, RemoteComicDataSource.QueryParams> {

    ComicMapper mComicMapper;

    public RemoteComicDataSource()
    {
        //This should be injected by dagger
        mComicMapper = new ComicMapper();
    }

    @Override
    public void getData(ResponseHandler<List<ComicEntity>> handler, QueryParams queryParams) {

        String ts = ""+new Date().getTime();
        String apikey = MarvelApiConsts.API_KEY;
        String hash = HashUtil.md5(ts + MarvelApiConsts.PRIVATE_KEY + MarvelApiConsts.API_KEY);

        Call<ComicsListResponse> call = MarvelApiFactory.getMarvelApi().getComics( ts, apikey, hash, queryParams.getOffset(), queryParams.getNumItems() );

        call.enqueue(new Callback<ComicsListResponse>() {
            @Override
            public void onResponse(Call<ComicsListResponse> call, Response<ComicsListResponse> response) {
                if(response.code()!=200)
                    handler.handle(Either.left(new ApiError("Error: "+response.code()+". "+response.message())));
                else
                    handler.handle(Either.right(mComicMapper.getComicsListEntityFromResponse(response.body())));
            }

            @Override
            public void onFailure(Call<ComicsListResponse> call, Throwable t) {
                handler.handle(Either.left(new ApiError(t.getMessage())));
            }
        });
    }

    public static class QueryParams{

        int mOffset;
        int mNumItems;

        public QueryParams(int offset, int numItems){
            mOffset = offset;
            mNumItems = numItems;
        }

        int getOffset(){
            return mOffset;
        }

        int getNumItems(){
            return mNumItems;
        }
    }
}
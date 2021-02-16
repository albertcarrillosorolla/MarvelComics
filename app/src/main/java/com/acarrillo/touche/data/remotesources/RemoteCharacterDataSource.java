package com.acarrillo.touche.data.remotesources;

import com.acarrillo.touche.data.DataSource;
import com.acarrillo.touche.data.exceptions.ApiError;
import com.acarrillo.touche.data.mappers.CharacterMapper;
import com.acarrillo.touche.data.remotesources.responses.CharactersListResponse;
import com.acarrillo.touche.data.utils.HashUtil;
import com.acarrillo.touche.domain.entities.CharacterEntity;
import com.acarrillo.touche.domain.utils.Either;
import com.acarrillo.touche.domain.utils.ResponseHandler;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteCharacterDataSource implements DataSource<List<CharacterEntity>, RemoteCharacterDataSource.QueryParams> {

    CharacterMapper mCharacterMapper;

    public RemoteCharacterDataSource() {
        //This should be injected by dagger
        mCharacterMapper = new CharacterMapper();
    }

    @Override
    public void getData(ResponseHandler<List<CharacterEntity>> handler, QueryParams queryParams) {

        String ts = ""+new Date().getTime();
        String apikey = MarvelApiConsts.API_KEY;
        String hash = HashUtil.md5(ts + MarvelApiConsts.PRIVATE_KEY + MarvelApiConsts.API_KEY);

        Call<CharactersListResponse> call = MarvelApiFactory.getMarvelApi().getCharactersByComicId(ts, apikey, hash, queryParams.getComicId() );

        call.enqueue(new Callback<CharactersListResponse>() {
            @Override
            public void onResponse(Call<CharactersListResponse> call, Response<CharactersListResponse> response) {
                if(response.code()!=200)
                    handler.handle(Either.left(new ApiError("Error: "+response.code()+". "+response.message())));
                else
                    handler.handle(Either.right(mCharacterMapper.getCharacterEntityListFromResponse(response.body())));
            }

            @Override
            public void onFailure(Call<CharactersListResponse> call, Throwable t) {
                handler.handle(Either.left(new ApiError(t.getMessage())));
            }
        });
    }

    public static class QueryParams{
        int mComicId;

        public QueryParams(int comicId){
            mComicId = comicId;
        }

        int getComicId(){
            return mComicId;
        }
    }
}

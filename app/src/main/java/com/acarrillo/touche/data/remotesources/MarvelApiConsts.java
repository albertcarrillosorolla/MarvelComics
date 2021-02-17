package com.acarrillo.touche.data.remotesources;

import com.acarrillo.touche.BuildConfig;

public class MarvelApiConsts {

    public static final int PORT = 443;
    public static final String BASE_URL = "https://gateway.marvel.com:"+PORT+"/";
    public static final String API_KEY = BuildConfig.MARVEL_API_PUBLIC_KEY;
    public static final String PRIVATE_KEY = BuildConfig.MARVEL_API_PRIVATE_KEY;

}
package com.icaboalo.barcodescanner.io;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by icaboalo on 21/06/16.
 */
public class ApiClient {

    private static ApiService mApiService;
    public static ApiService getApiService(){
        if (mApiService == null){
            Retrofit nRetrofit = new Retrofit.Builder()
                    .baseUrl("http://lupita.co/request/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApiService = nRetrofit.create(ApiService.class);
        }
        return mApiService;
    }
}

package com.icaboalo.barcodescanner.io;

import com.icaboalo.barcodescanner.io.model.ProductApiModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by icaboalo on 21/06/16.
 */
public interface ApiService {

    @GET("getProduct/{id}")
    Call<ProductApiModel> getProduct(@Path("id") String barcode);
}

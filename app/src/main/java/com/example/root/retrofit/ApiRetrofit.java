package com.example.root.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by root on 12/26/17.
 */

public interface ApiRetrofit {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponModel>kirim (@Field("nrp") String nrp,
                            @Field("nama") String nama,
                            @Field("penghasilan") String penghasila);

    @GET("read.php")
    Call <ResponModel> data();
    @FormUrlEncoded
    @POST("cari.php")
    Call<ResponModel>cari(@Field("search") String search);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponModel>update (@Field("nrp") String nrp,
                            @Field("nama") String nama,
                            @Field("penghasilan") String penghasila);


}

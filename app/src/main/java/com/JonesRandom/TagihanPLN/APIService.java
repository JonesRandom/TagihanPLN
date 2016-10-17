package com.JonesRandom.TagihanPLN;

import com.JonesRandom.TagihanPLN.Model.ResponseJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/tagihan-pln")
    Call<ResponseJSON> getDataTagihan(@Query("idp") String id, @Query("thn") String tahun,@Query("bln") String bulan);
}

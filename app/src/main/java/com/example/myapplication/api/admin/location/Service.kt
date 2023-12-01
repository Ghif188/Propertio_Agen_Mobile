package com.example.myapplication.api.admin.location

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("provinces.json")
    fun getProvinces(): Call<List<ProvinceResponse.Province>>

    @GET("regencies/{provinceId}.json")
    fun getRegency(@Path("provinceId") provinceId: String): Call<List<RegenciesResponse.Regency>>

    @GET("districts/{regencyId}.json")
    fun getDistrict(@Path("regencyId") regencyId: String): Call<List<DistrictsResponse.District>>
}
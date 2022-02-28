package com.example.ebaydemo.api.network

import com.example.ebaydemo.api.dataClass.EarthquakeDataClass
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman")
    fun getEarthquakeJson(): Single<EarthquakeDataClass>
}
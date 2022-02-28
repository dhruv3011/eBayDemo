package com.example.ebaydemo.view.main

import com.example.ebaydemo.api.dataClass.EarthquakeDataClass
import com.example.ebaydemo.api.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    val networkService: NetworkService
) {
    /**
     * Function to call network methods to get Earthquake data from api
     */
    fun getEarthquakeDataFromAPI(): Single<EarthquakeDataClass> = networkService.getEarthquakeJson()
}
package com.oli.testapp.feature.data.remote

import com.oli.testapp.feature.data.remote.dto.CoinDto
import retrofit2.http.GET

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

}
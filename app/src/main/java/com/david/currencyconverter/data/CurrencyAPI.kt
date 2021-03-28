package com.david.currencyconverter.data

import com.david.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {

    @GET("/latest")
    suspend fun getCurrentRates(@Query("base") base:String):Response<CurrencyResponse>
}
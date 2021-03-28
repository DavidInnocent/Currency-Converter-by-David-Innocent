package com.david.currencyconverter.ui.todo

import com.david.currencyconverter.data.models.CurrencyResponse
import com.david.currencyconverter.util.Resource

interface TodoInterfaceRepository {
    suspend fun getRates(base:String):Resource<CurrencyResponse>

}
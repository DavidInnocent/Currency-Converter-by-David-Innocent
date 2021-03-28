package com.david.currencyconverter.ui.todo

import com.david.currencyconverter.data.CurrencyAPI
import com.david.currencyconverter.data.models.CurrencyResponse
import com.david.currencyconverter.util.Resource
import java.lang.Exception
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val api: CurrencyAPI):TodoInterfaceRepository {
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
      return try {

          val response=api.getCurrentRates(base)
          val result=response.body()
          if(response.isSuccessful&&result!=null)
          {
              Resource.Success(result)
          }
          else
          {
              Resource.Error(response.message())
          }
      }
      catch (e:Exception){
          Resource.Error(e.message?:"An error occured retry again")
      }
    }
}
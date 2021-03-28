package com.david.currencyconverter.di

import com.david.currencyconverter.TodoApplication
import com.david.currencyconverter.data.CurrencyAPI
import com.david.currencyconverter.ui.todo.DispatcherProvider
import com.david.currencyconverter.ui.todo.TodoInterfaceRepository
import com.david.currencyconverter.ui.todo.TodoRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyAPI():CurrencyAPI=Retrofit.Builder()
        .baseUrl("https://api.exchangeratesapi.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyAPI::class.java)


    @Singleton
    @Provides
    fun provideCurrencyRepository(api: CurrencyAPI):TodoInterfaceRepository=TodoRepository(api)
  @Singleton
    @Provides
    fun provideDispatchers():DispatcherProvider=object :DispatcherProvider{
      override val main: CoroutineDispatcher
          get() = Dispatchers.Main
      override val io: CoroutineDispatcher
          get() =Dispatchers.IO
      override val default: CoroutineDispatcher
          get() =Dispatchers.Default
      override val unconfined: CoroutineDispatcher
          get() = Dispatchers.Unconfined

  }

}
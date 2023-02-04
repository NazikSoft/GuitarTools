package com.naziksoft.guitartools.di

import com.naziksoft.guitartools.api.RemoteApi
import com.naziksoft.guitartools.repository.GuitarToolsRepository
import com.naziksoft.guitartools.utils.Constants
import com.naziksoft.guitartools.viewmodel.SearchResultViewModel
import com.naziksoft.guitartools.viewmodel.SearchViewModel
import com.naziksoft.guitartools.viewmodel.WebViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<RemoteApi> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor (interceptor)
            .build()
        Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
     }

    single<GuitarToolsRepository> { GuitarToolsRepository(get()) }

    viewModel { SearchResultViewModel() }
    viewModel { SearchViewModel(get()) }
    viewModel { WebViewModel(get()) }
}
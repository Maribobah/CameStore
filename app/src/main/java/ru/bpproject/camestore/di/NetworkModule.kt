package ru.bpproject.camestore.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import ru.bpproject.camestore.data.extdb.ApiService
import ru.bpproject.camestore.data.extdb.BasicAuthInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): ApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideConverter(): Converter.Factory {
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
            .asConverterFactory("application/json".toMediaType())
    }

    companion object {
        private const val BASE_URL = "http://192.168.43.188/ASC/hs/ext/"
    }
}
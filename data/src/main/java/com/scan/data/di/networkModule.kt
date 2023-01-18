package com.scan.data.di

import com.google.gson.GsonBuilder
import com.scan.base.utils.NetworkConnectivityHelper
import com.scan.data.BuildConfig
import com.scan.data.network.api.Api
import com.scan.data.network.constant.BASE_URL
import com.scan.data.network.constant.DEFAULT_NETWORK_TIMEOUT_SECONDS
import com.scan.data.network.interceptors.DefaultInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Koin module for okHttp , retrofit and its api interfaces
 */
val networkModule = module {

    fun provideOkHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun provideOkHttpDefaultInterceptor(
        versionName: String,
        versionCode: Int,
        networkConnectivityHelper: NetworkConnectivityHelper,
    ) = DefaultInterceptor(
        versionName,
        versionCode,
        networkConnectivityHelper,
    )

    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        defaultInterceptor: DefaultInterceptor,
        timeout: Long
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(defaultInterceptor)
            .build()
    }

    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single(named(DI_BASE_URL)) { BASE_URL }


    single { provideOkHttpLoggingInterceptor() }

    single {
        provideOkHttpDefaultInterceptor(
            get(named(DI_VERSION_NAME)), get(named(DI_VERSION_CODE)), get()
        )
    }

    single { (timeout: Long) -> provideOkHttpClient(get(), get(), timeout) }

    single(named(DI_RETROFIT)) { (timeout: Long) ->
        provideRetrofit(
            get(named(DI_BASE_URL)),
            get(parameters = { parametersOf(timeout) })
        )
    }

    factory {
        get<Retrofit>(
            named(DI_RETROFIT),
            parameters = { parametersOf(DEFAULT_NETWORK_TIMEOUT_SECONDS) }
        ).create(Api::class.java)
    }
}

const val DI_BASE_URL = "BASE_URL"
const val DI_RETROFIT = "RETROFIT"
const val DI_VERSION_NAME = "VERSION_NAME"
const val DI_VERSION_CODE = "VERSION_CODE"

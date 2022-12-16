package com.example.repositoryexample

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class IgtiApplication : Application() {

    companion object {
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15L, TimeUnit.SECONDS)
            .readTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor { msg ->
                println("LOG APP $msg")
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addNetworkInterceptor(HttpLoggingInterceptor {
                println("LOG NTW: $it")
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://igti.com.br")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
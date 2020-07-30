package com.example.firstapp

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
    private var gsonConverterFactory: GsonConverterFactory
    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Movies::class.java, MovieJsonDeserializer())
        val gson = gsonBuilder.create()
        gsonConverterFactory = GsonConverterFactory.create(gson)
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.tvmaze.com")
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}
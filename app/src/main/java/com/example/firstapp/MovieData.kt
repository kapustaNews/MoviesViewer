package com.example.firstapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieData {

    @GET("/search/shows")
    fun getMovies(@Query("q") q: String): Call<Movies>
}
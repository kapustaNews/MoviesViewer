package com.example.firstapp

data class Movies(val movies: List<Movie>)
data class Movie(val name: String, val genre: List<String>, val image: String,
                 val rating: Double, var like: Boolean)



package com.example.firstapp

import android.content.ClipData.Item
import android.icu.number.IntegerWidth
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type


class MovieJsonDeserializer:JsonDeserializer<Movies> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Movies {
        val shows = json.asJsonArray
        val showsList = mutableListOf<Movie>()
        for (item in shows){
            val showJsonObject = item.asJsonObject.get("show").asJsonObject
            var rating: Double = 0.0
            if (!showJsonObject.get("rating").isJsonNull) {
                val ratingJsonObject = showJsonObject.get("rating").asJsonObject
                if (!ratingJsonObject.get("average").isJsonNull){
                    rating = ratingJsonObject.get("average").asDouble
                }
            }
            val name = showJsonObject.get("name").asString
            val genre = showJsonObject.get("genres").asJsonArray
            val genreList = mutableListOf<String>()
            for (itemsJsonElement in genre) {
                val itemJsonObject = itemsJsonElement.asString
                genreList.add(itemJsonObject)
            }
            var image: String = ""
            if (!showJsonObject.get("image").isJsonNull){
                val imageJsonObject = showJsonObject.get("image").asJsonObject
                image = imageJsonObject.get("medium").asString
            }
            showsList.add(Movie(name,genreList,image, rating, false))
        }
     return Movies(showsList)
    }
}
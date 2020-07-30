package com.example.firstapp.adappter

import android.animation.LayoutTransition
import android.graphics.ImageFormat
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.Movie
import com.example.firstapp.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val movieList : List<Movie>) : RecyclerView.Adapter<MovieViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val movieItemLayout = inflater.inflate(R.layout.movie_item, parent,false)
        val titleTextView = movieItemLayout.findViewById<TextView>(R.id.list_title)
        val genreTextView = movieItemLayout.findViewById<TextView>(R.id.list_genre)
        val imageImageView = movieItemLayout.findViewById<ImageView>(R.id.image_movie)
        val ratingTextView = movieItemLayout.findViewById<TextView>(R.id.rating)
        val likeView = movieItemLayout.findViewById<ImageView>(R.id.like)
        val movieViewHolder = MovieViewHolder(titleTextView, genreTextView, imageImageView,
            ratingTextView, likeView, movieItemLayout)
        return movieViewHolder
    }



    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.titleTextView.text = movieList[position].name
        holder.genreTextView.text = movieList[position].genre.toString().replace("[","" ).replace("]","")
        if(movieList[position].rating == 0.0){
            holder.ratingTextView.text = "Rating: N/A"
        } else {
            holder.ratingTextView.text = "Rating: " + movieList[position].rating.toString()
        }
        if(movieList[position].like) {
            holder.likeImageView.setImageResource(R.drawable.heart_color)
        }else {
            holder.likeImageView.setImageResource(R.drawable.heart)
        }
        holder.likeImageView.setOnClickListener{
            if(movieList[position].like) {
                movieList[position].like = false
                holder.likeImageView.setImageResource(R.drawable.heart)
            }else {
                movieList[position].like = true
                holder.likeImageView.setImageResource(R.drawable.heart_color)
            }
        }

        Glide.with(holder.imageImageView).load(movieList[position].image).into(holder.imageImageView)
    }

}
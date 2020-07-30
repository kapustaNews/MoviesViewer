package com.example.firstapp.adappter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class MovieViewHolder(val titleTextView: TextView,
                           val genreTextView: TextView,
                           val imageImageView: ImageView,
                           val ratingTextView: TextView,
                           val likeImageView: ImageView,
                           var itemView: View): RecyclerView.ViewHolder(itemView)
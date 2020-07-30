package com.example.firstapp


import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.adappter.MovieAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var viewAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        val movieList = mutableListOf<Movie>()
        viewAdapter = MovieAdapter(movieList)
        movieRecyclerView = findViewById<RecyclerView>(R.id.rv_movie).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        val dividerItemDecoration = DividerItemDecoration(
            movieRecyclerView.getContext(),
            viewManager.getOrientation()
        )
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(movieRecyclerView.context,R.drawable.line)!!)
        movieRecyclerView.addItemDecoration(dividerItemDecoration)

        searchView = findViewById<SearchView>(R.id.film_search)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                val parentLayout: View = findViewById(android.R.id.content)
                movieList.clear()

                val request = ServiceBuilder.buildService(MovieData::class.java)
                text?.let {
                    if (it.length >= 2) {
                        progress_bar.visibility = View.VISIBLE
                        val call = request.getMovies(it)
                        call.enqueue(object : Callback<Movies> {
                            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                                if (response.isSuccessful) {
                                    progress_bar.visibility = View.GONE
                                    rv_movie.apply {
                                        setHasFixedSize(true)
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                        adapter = MovieAdapter(response.body()!!.movies)
                                    }
                                }
                            }

                            override fun onFailure(call: Call<Movies>, t: Throwable) {

                            }
                        })
                    }

                    // viewAdapter.notifyDataSetChanged()
                }
                Snackbar.make(parentLayout, "Submit: $text", Snackbar.LENGTH_LONG).show();
                return true;
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return onQueryTextSubmit(text)
            }
        })
    }
}
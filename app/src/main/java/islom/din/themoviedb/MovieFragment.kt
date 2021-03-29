package islom.din.themoviedb

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var movieViewModel: MovieViewModel
    private var moviePosition: Int? = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviePosition = arguments?.getInt("moviePosition", 0)

        movieViewModel = ViewModelProvider(
            requireActivity()
        ).get(MovieViewModel::class.java)

        movieViewModel.listOfMoviesLiveData.observe(requireActivity()) {
            val movie = it[moviePosition!!]
            initMovieData(view, movie)
        }

        view.back_button.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun initMovieData(view: View, movie: Result) {
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/w500/${movie.backdrop_path}")
            .centerCrop()
            .into(view.background_image)

        Glide.with(view)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .centerCrop()
            .into(view.image)

        view.name.text = movie.original_name
        view.year.text = movie.first_air_date
        view.popularity.text = "Popularity: ${movie.popularity}"
        view.vote_count.text = "Vote count: ${movie.vote_count}"
        var resultText = "Countries: "
        for(item in movie.origin_country!!) {
            resultText += "$item "
        }
        view.vote.text = movie.vote_average.toString()
        view.overview.text = movie.overview
    }


}
package islom.din.themoviedb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
            view.text.text = "${movie.name}\n${movie.overview}"
            setText(movie)
        }
    }

    private fun setText(movie: Result) {

    }

}
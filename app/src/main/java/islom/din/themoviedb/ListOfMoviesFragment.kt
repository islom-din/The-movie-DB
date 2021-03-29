package islom.din.themoviedb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_listofmovies.*

class ListOfMoviesFragment : Fragment(R.layout.fragment_listofmovies) {

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(
            requireActivity()
        ).get(MovieViewModel::class.java)

        movieAdapter = MovieAdapter(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putInt("moviePosition", position)
                findNavController().navigate(
                    R.id.action_listOfMoviesFragment_to_movieFragment,
                    bundle
                )
            }
        })

        movieViewModel.initLiveData()

        movieViewModel.listOfMoviesLiveData.observe(requireActivity()) {
            movieAdapter.setListOfMovies(it)
        }

        initUiList()
    }

    private fun initUiList() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = movieAdapter
        }
    }
}
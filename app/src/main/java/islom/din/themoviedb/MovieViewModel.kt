package islom.din.themoviedb

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * */
class MovieViewModel: ViewModel() {

    private val LANGUAGE = "en-US"
    private val PAGE = 1
    private val API_KEY = "081eb3b82c8f61d0f72cb87fd140ce71"

    val listOfMoviesLiveData = MutableLiveData<List<Result>>()

    fun initLiveData() {
        setListOfMovies()
    }

    private fun setListOfMovies() {
        RetrofitClient
            .getMovieApi()
            .getListOfMovies(LANGUAGE, PAGE, API_KEY)
            .enqueue(object : Callback<Root> {
                override fun onResponse(
                    call: Call<Root>,
                    response: Response<Root>
                ) {
                    if (!response.isSuccessful) return

                    if (response.body() != null) {
                        val list = response.body()!!.results
                        listOfMoviesLiveData.postValue(list)
                    }
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    Log.d("MovieRepository", "onFailure: ${t.message}")
                }
            })
    }
}
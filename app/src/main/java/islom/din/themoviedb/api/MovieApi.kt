package islom.din.themoviedb.api

import islom.din.themoviedb.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("3/tv/popular")
    fun getListOfMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<Root>

}

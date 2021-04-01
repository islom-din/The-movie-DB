package islom.din.themoviedb

class Result {
    var backdrop_path: String? = null
    var first_air_date: String? = null
    var genre_ids: List<Int>? = null
    var id = 0
    var name: String? = null
    var origin_country: List<String>? = null
    var original_language: String? = null
    var original_name: String? = null
    var overview: String? = null
    var popularity = 0.0
    var poster_path: String? = null
    var vote_average = 0.0
    var vote_count = 0
}

class Root {
    var page = 0
    var results: List<Result>? = null
    var total_pages = 0
    var total_results = 0
}

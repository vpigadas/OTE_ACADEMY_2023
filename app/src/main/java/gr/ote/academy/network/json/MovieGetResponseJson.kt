package gr.ote.academy.network.json

data class MoviesResponseJson(
    val results: List<MovieResponseJson>
)

data class MovieResponseJson(
    val id: String? = null,
    val original_title: String? = null,
    val overview: String? = null
)
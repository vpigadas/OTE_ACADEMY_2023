package gr.ote.academy.mvvm.json

data class MovieDataModel(
    val results: List<MovieItemDataModel>
)

data class MovieItemDataModel(
    val id: Long,
    val overview: String,
    val title: String,
    val poster_path: String
)
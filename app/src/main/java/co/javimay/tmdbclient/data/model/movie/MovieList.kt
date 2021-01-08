package co.javimay.tmdbclient.data.model.movie

import co.javimay.tmdbclient.utils.serializedNames
import com.google.gson.annotations.SerializedName

data class MovieList(
    val page: Int,
    @SerializedName(serializedNames.RESULTS)
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
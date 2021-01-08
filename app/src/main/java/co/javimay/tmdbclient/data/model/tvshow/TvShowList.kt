package co.javimay.tmdbclient.data.model.tvshow

import co.javimay.tmdbclient.utils.serializedNames
import com.google.gson.annotations.SerializedName

data class TvShowList(
    val page: Int,
    @SerializedName(serializedNames.RESULTS)
    val tvShows: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)
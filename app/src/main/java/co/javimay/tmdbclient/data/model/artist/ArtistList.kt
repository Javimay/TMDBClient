package co.javimay.tmdbclient.data.model.artist

import co.javimay.tmdbclient.utils.serializedNames
import com.google.gson.annotations.SerializedName

data class ArtistList(
    val page: Int,
    @SerializedName(serializedNames.RESULTS)
    val artists: List<Artist>,
    val total_pages: Int,
    val total_results: Int
)
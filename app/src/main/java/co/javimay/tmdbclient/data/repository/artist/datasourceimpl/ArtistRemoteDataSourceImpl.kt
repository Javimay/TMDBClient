package co.javimay.tmdbclient.data.repository.artist.datasourceimpl

import co.javimay.tmdbclient.data.api.TMDBService
import co.javimay.tmdbclient.data.model.artist.ArtistList
import co.javimay.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
):ArtistRemoteDataSource {
    override suspend fun getArtist(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)
}
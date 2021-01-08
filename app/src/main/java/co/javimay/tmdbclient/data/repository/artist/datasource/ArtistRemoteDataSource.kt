package co.javimay.tmdbclient.data.repository.artist.datasource

import co.javimay.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun getArtist(): Response<ArtistList>
}
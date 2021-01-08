package co.javimay.tmdbclient.domain.repository

import co.javimay.tmdbclient.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtist(): List<Artist>?
    suspend fun updateArtist(): List<Artist>?
}
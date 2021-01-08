package co.javimay.tmdbclient.domain.usescases.artist

import co.javimay.tmdbclient.data.model.artist.Artist
import co.javimay.tmdbclient.domain.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {

    suspend fun execute():List<Artist>? = artistRepository.getArtist()
}
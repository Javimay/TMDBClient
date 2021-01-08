package co.javimay.tmdbclient.domain.usescases.tvshows

import co.javimay.tmdbclient.data.model.tvshow.TvShow
import co.javimay.tmdbclient.domain.repository.TvShowRepository

class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute():List<TvShow>? = tvShowRepository.getTvShows()
}
package co.javimay.tmdbclient.data.repository.tvshows.datasourceimpl

import co.javimay.tmdbclient.data.api.TMDBService
import co.javimay.tmdbclient.data.model.tvshow.TvShowList
import co.javimay.tmdbclient.data.repository.tvshows.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
):TvShowRemoteDataSource {

    override suspend fun getTvShows(): Response<TvShowList> =tmdbService.getPopularTvShows(apiKey)
}
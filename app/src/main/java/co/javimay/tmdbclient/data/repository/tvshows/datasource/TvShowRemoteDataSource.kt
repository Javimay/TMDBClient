package co.javimay.tmdbclient.data.repository.tvshows.datasource

import co.javimay.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}
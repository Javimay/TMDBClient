package co.javimay.tmdbclient.data.repository.tvshows.datasourceimpl

import co.javimay.tmdbclient.data.model.tvshow.TvShow
import co.javimay.tmdbclient.data.repository.tvshows.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl:TvShowCacheDataSource {

    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getTvShowsFromCache(): List<TvShow> = tvShowList

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }
}
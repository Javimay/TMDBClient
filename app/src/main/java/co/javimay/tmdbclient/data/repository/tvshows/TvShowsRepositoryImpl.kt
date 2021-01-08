package co.javimay.tmdbclient.data.repository.tvshows

import android.util.Log
import co.javimay.tmdbclient.data.model.tvshow.TvShow
import co.javimay.tmdbclient.data.model.tvshow.TvShowList
import co.javimay.tmdbclient.data.repository.tvshows.datasource.TvShowCacheDataSource
import co.javimay.tmdbclient.data.repository.tvshows.datasource.TvShowLocalDataSource
import co.javimay.tmdbclient.data.repository.tvshows.datasource.TvShowRemoteDataSource
import co.javimay.tmdbclient.domain.repository.TvShowRepository
import retrofit2.Response
import java.lang.Exception

class TvShowsRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
):TvShowRepository {

    companion object{
        val TAG = TvShowsRepositoryImpl::class.simpleName
    }

    override suspend fun getTvShows(): List<TvShow>? = getTvShowsFromCache()

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShows: List<TvShow> = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI(): List<TvShow>{
        lateinit var tvShowsList: List<TvShow>
        try {
            val response : Response<TvShowList> = tvShowRemoteDataSource.getTvShows()
            val body: TvShowList? = response.body()
            if (body != null){
                tvShowsList = body.tvShows
            }
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        return tvShowsList
    }

    suspend fun getTvShowsFromDB(): List<TvShow>{
        lateinit var tvShowsList: List<TvShow>
        try {
            tvShowsList = tvShowLocalDataSource.getTvShowsFromDB()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (tvShowsList.isEmpty()){
            tvShowsList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowsList)
        }
        return tvShowsList
    }

    suspend fun getTvShowsFromCache():List<TvShow>{
        lateinit var tvShowsList: List<TvShow>
        try {
            tvShowsList = tvShowCacheDataSource.getTvShowsFromCache()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (tvShowsList.isEmpty()){
            tvShowsList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowsList)
        }
        return tvShowsList
    }
}
package co.javimay.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.javimay.tmdbclient.data.model.tvshow.TvShow
import co.javimay.tmdbclient.utils.tableNames

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvshows: List<TvShow>)

    @Query("DELETE FROM ${tableNames.POPULAR_TVSHOWS}")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM ${tableNames.POPULAR_TVSHOWS}")
    suspend fun getTvShows(): List<TvShow>
}
package co.javimay.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.javimay.tmdbclient.data.model.movie.Movie
import co.javimay.tmdbclient.utils.tableNames

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM ${tableNames.POPULAR_MOVIES}")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM ${tableNames.POPULAR_MOVIES}")
    suspend fun getMovies():List<Movie>
}

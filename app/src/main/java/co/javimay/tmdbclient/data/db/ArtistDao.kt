package co.javimay.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.javimay.tmdbclient.data.model.artist.Artist
import co.javimay.tmdbclient.utils.tableNames

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM ${tableNames.POPULAR_ARTISTS}")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM ${tableNames.POPULAR_ARTISTS}")
    suspend fun getArtists(): List<Artist>
}
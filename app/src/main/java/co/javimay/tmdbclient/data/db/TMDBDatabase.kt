package co.javimay.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.javimay.tmdbclient.data.model.artist.Artist
import co.javimay.tmdbclient.data.model.movie.Movie
import co.javimay.tmdbclient.data.model.tvshow.TvShow

@Database(
    entities = [Movie::class, TvShow::class, Artist::class],
    version = 1,
    exportSchema = false)
abstract class TMDBDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvShowsDao(): MovieDao

    abstract fun artistDao(): ArtistDao
}
package co.javimay.tmdbclient.data.repository.artist.datasourceimpl

import co.javimay.tmdbclient.data.db.ArtistDao
import co.javimay.tmdbclient.data.model.artist.Artist
import co.javimay.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao):ArtistLocalDataSource {

    override suspend fun getArtistFromDB(): List<Artist> = artistDao.getArtists()

    override suspend fun saveArtistToDB(artist: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artist)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}
package co.javimay.tmdbclient.data.repository.artist

import android.util.Log
import co.javimay.tmdbclient.data.model.artist.Artist
import co.javimay.tmdbclient.data.model.artist.ArtistList
import co.javimay.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import co.javimay.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import co.javimay.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import co.javimay.tmdbclient.domain.repository.ArtistRepository
import retrofit2.Response
import java.lang.Exception

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
):ArtistRepository {
    companion object{
        val TAG = ArtistRepositoryImpl::class.simpleName
    }

    override suspend fun getArtist(): List<Artist>? = getArtistFromCache()

    override suspend fun updateArtist(): List<Artist>? {
        val newListOfArtists: List<Artist> = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListOfArtists)
        artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }

    suspend fun getArtistsFromAPI(): List<Artist>{
        lateinit var artistsList: List<Artist>
        try {
           val response : Response<ArtistList> = artistRemoteDataSource.getArtist()
           val body: ArtistList? = response.body()
           if (body != null){
               artistsList = body.artists
           }
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        return artistsList
    }

    suspend fun getArtistsFromDB(): List<Artist>{
        lateinit var artistsList: List<Artist>
        try {
            artistsList = artistLocalDataSource.getArtistFromDB()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (artistsList.isEmpty()){
            artistsList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistToDB(artistsList)
        }
        return artistsList
    }

    suspend fun getArtistFromCache(): List<Artist>{
        lateinit var artistsList: List<Artist>
        try {
            artistsList = artistCacheDataSource.getArtistsFromCache()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (artistsList.isEmpty()){
            artistsList= getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistsList)
        }
        return artistsList
    }
}
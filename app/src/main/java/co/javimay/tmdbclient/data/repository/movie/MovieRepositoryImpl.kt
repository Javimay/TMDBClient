package co.javimay.tmdbclient.data.repository.movie

import android.util.Log
import co.javimay.tmdbclient.data.model.movie.Movie
import co.javimay.tmdbclient.data.model.movie.MovieList
import co.javimay.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import co.javimay.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import co.javimay.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import co.javimay.tmdbclient.domain.repository.MovieRepository
import retrofit2.Response
import java.lang.Exception

class MovieRepositoryImpl(
    private val moviesRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
): MovieRepository {

    companion object{
        val TAG = MovieRepositoryImpl::class.simpleName
    }

    override suspend fun getMovies(): List<Movie> = getMoviesFromCache()

    override suspend fun updateMovies(): List<Movie> {
        val newListOfMovies: List<Movie> = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            val response : Response<MovieList> = moviesRemoteDataSource.getMovies()
            val body: MovieList? = response.body()
            if (body != null){
                movieList = body.movies
            }
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (movieList.isEmpty()){
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    suspend fun getMoviesFromCache():List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (movieList.isEmpty()){
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}
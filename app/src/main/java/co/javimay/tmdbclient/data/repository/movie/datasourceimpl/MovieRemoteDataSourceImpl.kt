package co.javimay.tmdbclient.data.repository.movie.datasourceimpl

import co.javimay.tmdbclient.data.api.TMDBService
import co.javimay.tmdbclient.data.model.movie.MovieList
import co.javimay.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
    ): MovieRemoteDataSource {

    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}
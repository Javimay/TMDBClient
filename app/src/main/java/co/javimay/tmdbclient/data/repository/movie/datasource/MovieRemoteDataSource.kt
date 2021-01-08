package co.javimay.tmdbclient.data.repository.movie.datasource

import co.javimay.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies(): Response<MovieList>
}
package co.javimay.tmdbclient.domain.repository

import co.javimay.tmdbclient.data.model.movie.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun updateMovies(): List<Movie>
}
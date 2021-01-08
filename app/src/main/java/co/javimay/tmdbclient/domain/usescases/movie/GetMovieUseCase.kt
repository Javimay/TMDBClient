package co.javimay.tmdbclient.domain.usescases.movie

import co.javimay.tmdbclient.data.model.movie.Movie
import co.javimay.tmdbclient.domain.repository.MovieRepository

class GetMovieUseCase (private val movieRepository: MovieRepository){

    suspend fun execute():List<Movie>? = movieRepository.getMovies()
}
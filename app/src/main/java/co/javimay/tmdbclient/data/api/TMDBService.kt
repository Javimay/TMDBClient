package co.javimay.tmdbclient.data.api

import co.javimay.tmdbclient.data.model.artist.ArtistList
import co.javimay.tmdbclient.data.model.movie.MovieList
import co.javimay.tmdbclient.data.model.tvshow.TvShowList
import co.javimay.tmdbclient.utils.serializedNames
import co.javimay.tmdbclient.utils.servicesEndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET(servicesEndPoints.GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(@Query(serializedNames.API_KEY)apiKey:String):Response<MovieList>

    @GET(servicesEndPoints.GET_POPULAR_TVSHOWS)
    suspend fun getPopularTvShows(@Query(serializedNames.API_KEY)apiKey:String):Response<TvShowList>

    @GET(servicesEndPoints.GET_POPULAR_ARTISTS)
    suspend fun getPopularArtists(@Query(serializedNames.API_KEY)apiKey:String):Response<ArtistList>
}
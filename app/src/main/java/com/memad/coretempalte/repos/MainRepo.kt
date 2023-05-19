/*
package com.memad.coretempalte.repos

import com.memad.coretempalte.data.local.FavouritesEntity
import com.memad.coretempalte.data.local.MovieEntity
import com.memad.coretempalte.models.MoviesResponse
import com.memad.coretempalte.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepo {

    suspend fun favouriteAMovie(movie: FavouritesEntity): Long

    suspend fun unFavouriteAMovie(movieId: Int)

    suspend fun getAllMovies(
        page: Int
    ): Flow<Resource<out List<MovieEntity>>>
}
*/

package com.memad.coretempalte.utils

import com.memad.coretempalte.data.local.MovieEntity
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper
import javax.inject.Inject
import javax.inject.Singleton

/*@Singleton
class SuccessMoviesMapper @Inject constructor(private val movieType: Int) :
    ApiSuccessModelMapper<MoviesResponse, List<MovieEntity>> {

    override fun map(apiSuccessResponse: ApiResponse.Success<MoviesResponse>): List<MovieEntity> {
        return apiSuccessResponse.data.results.map {
            MovieEntity(it.id, movieType, apiSuccessResponse.data.page, it)
        }
    }
}*/


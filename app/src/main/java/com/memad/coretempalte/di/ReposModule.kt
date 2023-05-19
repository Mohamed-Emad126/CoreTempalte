package com.memad.coretempalte.di

import com.memad.coretempalte.data.local.MoviesDao
import com.memad.coretempalte.di.annotations.*
import com.memad.coretempalte.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReposModule {

    /*@PopularRepo
    @Singleton
    @Provides
    fun providePopularRepo(
        moviesDao: MoviesDao,
        moviesClient: MoviesClient
    ): MainRepo {
        return MainRepoImpl(moviesDao, moviesClient, Constants.POPULAR)
    }*/
}
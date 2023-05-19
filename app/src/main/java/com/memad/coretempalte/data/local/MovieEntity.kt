package com.memad.coretempalte.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "MOVIE_TABLE")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int?,
    @ColumnInfo(name = "movie_type") val movieType: Int,
    @ColumnInfo(name = "movie_page") val moviePage: Int
)
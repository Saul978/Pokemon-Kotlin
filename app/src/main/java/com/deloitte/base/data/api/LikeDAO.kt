package com.deloitte.base.data.api

import androidx.room.*
import com.deloitte.base.domain.entity.Likes

@Dao
interface LikeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(like: Likes)

    @Delete
    fun delete(like: Likes)

    @Query("SELECT * FROM likes WHERE pokemonId = :pokemonId")
    fun getLike(pokemonId: Int): Likes?
}
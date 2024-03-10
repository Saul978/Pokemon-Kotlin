package com.deloitte.base.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likes")
data class Likes(
    @PrimaryKey val pokemonId: Int = 0
)

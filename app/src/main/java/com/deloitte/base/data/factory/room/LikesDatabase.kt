package com.deloitte.base.data.factory.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deloitte.base.data.api.LikeDAO
import com.deloitte.base.domain.entity.Likes

@Database(entities = [Likes::class], version = 1, exportSchema = false)
abstract class LikesDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDAO
}
package com.ixs.testing.hilt.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable

@Database(
    entities = [BlogCacheEntity::class],
    version = 1
)
abstract class BlogDatabase :RoomDatabase(){

    abstract fun blogDao() : BlogDao

    companion object{
        val DATABASE_NAME : String = "Blog.db"
    }

}
package com.ixs.testing.hilt.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBlog(blog : BlogCacheEntity) :Long

    @Query("SELECT * FROM blog")
    suspend fun getBlog(): List<BlogCacheEntity>
}
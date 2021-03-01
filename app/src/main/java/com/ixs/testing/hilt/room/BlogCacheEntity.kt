package com.ixs.testing.hilt.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog")
data class BlogCacheEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "category")
    var category: String,

)
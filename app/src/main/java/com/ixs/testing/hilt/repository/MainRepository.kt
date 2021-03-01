package com.ixs.testing.hilt.repository

import com.ixs.testing.hilt.model.Blog
import com.ixs.testing.hilt.retrofit.BlogNetworkEntity
import com.ixs.testing.hilt.retrofit.BlogRetrofit
import com.ixs.testing.hilt.retrofit.NetworkMapper
import com.ixs.testing.hilt.room.BlogCacheMapper
import com.ixs.testing.hilt.room.BlogDao
import com.ixs.testing.hilt.room.BlogDatabase
import com.ixs.testing.hilt.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository
constructor(
        private val blogDao: BlogDao,
        private val blogRetrofit: BlogRetrofit,
        private val cacheMapper: BlogCacheMapper,
       private val networkMapper: NetworkMapper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow{
        emit(DataState.loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.saveBlog(cacheMapper.mapToEntity(blog))
            }
            val cachedBlog = blogDao.getBlog()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlog)))
        }
        catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

}
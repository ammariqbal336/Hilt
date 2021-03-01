package com.ixs.testing.hilt.di

import com.ixs.testing.hilt.repository.MainRepository
import com.ixs.testing.hilt.retrofit.BlogRetrofit
import com.ixs.testing.hilt.retrofit.NetworkMapper
import com.ixs.testing.hilt.room.BlogCacheMapper
import com.ixs.testing.hilt.room.BlogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule{

    @Singleton
    @Provides
     fun getRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: BlogCacheMapper,
        networkMapper: NetworkMapper
    ):MainRepository{
        return MainRepository(blogDao,blogRetrofit,cacheMapper,networkMapper)
    }
}
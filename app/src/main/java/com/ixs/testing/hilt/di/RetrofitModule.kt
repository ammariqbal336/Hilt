package com.ixs.testing.hilt.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ixs.testing.hilt.retrofit.BlogRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return  GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun RetrofitBuilder(gson: Gson):Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofitbuilder:Retrofit.Builder): BlogRetrofit{
        return retrofitbuilder
            .build()
            .create(BlogRetrofit::class.java)
    }

}
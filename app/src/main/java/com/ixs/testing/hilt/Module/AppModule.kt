package com.ixs.testing.hilt.Module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule{

    @Singleton
    @Provides
    fun provideString() :String{
        return "Something"
    }
}
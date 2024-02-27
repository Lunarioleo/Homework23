package com.example.homework23

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MyModule {


    @Provides
    @Singleton
    fun getRepository(apiClient: ApiClient) = MyRepository(apiClient)
}
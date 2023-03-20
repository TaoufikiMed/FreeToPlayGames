package com.example.freetoplaygames.di

import com.example.freetoplaygames.common.Constants
import com.example.freetoplaygames.data.remote.FreeToPlayApi
import com.example.freetoplaygames.data.repository.GameRepositoryImpl
import com.example.freetoplaygames.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFreeToPlayApi() : FreeToPlayApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FreeToPlayApi::class.java)
    }
    @Singleton
    @Provides
    fun provideGameRepository(api : FreeToPlayApi) : GameRepository {
        return GameRepositoryImpl(api)
    }
}
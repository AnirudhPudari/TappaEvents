package com.eazybytes.data.di

import com.eazybytes.data.api.EventApi
import com.eazybytes.data.repository.EventRepositoryImpl
import com.eazybytes.domain.common.Constants.BASE_URL
import com.eazybytes.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataSource(): EventApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: EventApi): EventRepository {
        return EventRepositoryImpl(api)
    }
}
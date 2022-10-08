package com.mycariadtask.application

import com.mycariadtask.core.Constants
import com.mycariadtask.feature_parking_spots.data.remote.ParkingSpotApi
import com.mycariadtask.feature_parking_spots.data.repository.ParkingSpotRepositoryImpl
import com.mycariadtask.feature_parking_spots.domain.repository.ParkingSpotDomainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ParkingSpotAppModule {
    @Provides
    @Singleton
    fun getParkingSpotApi(): ParkingSpotApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ParkingSpotApi::class.java)
    }

    @Provides
    @Singleton
    fun getParkingSpotDomainRepository(api: ParkingSpotApi): ParkingSpotDomainRepository {
        return ParkingSpotRepositoryImpl(api)
    }
}
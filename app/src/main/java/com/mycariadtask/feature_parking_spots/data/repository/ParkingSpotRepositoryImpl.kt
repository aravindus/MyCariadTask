package com.mycariadtask.feature_parking_spots.data.repository

import com.mycariadtask.core.Constants
import com.mycariadtask.feature_parking_spots.data.datasource.dto.ResponseParkingSpotItemDto
import com.mycariadtask.feature_parking_spots.data.remote.ParkingSpotApi
import com.mycariadtask.feature_parking_spots.domain.repository.ParkingSpotDomainRepository
import javax.inject.Inject

class ParkingSpotRepositoryImpl @Inject constructor(private val api: ParkingSpotApi) :
    ParkingSpotDomainRepository {
    override suspend fun getParkingSpots(): List<ResponseParkingSpotItemDto> {
        return api.getParkingSpot(
            Constants.API_KEY,
            Constants.Latitude,
            Constants.Longitude,
            Constants.DISTANCE,
            10)
    }
}
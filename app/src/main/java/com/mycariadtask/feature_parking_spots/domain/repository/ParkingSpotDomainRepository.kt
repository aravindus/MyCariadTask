package com.mycariadtask.feature_parking_spots.domain.repository

import com.mycariadtask.feature_parking_spots.data.datasource.dto.ResponseParkingSpotItemDto

interface ParkingSpotDomainRepository {
    suspend fun getParkingSpots(): List<ResponseParkingSpotItemDto>
}
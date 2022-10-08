package com.mycariadtask.feature_parking_spots.domain.model

import com.mycariadtask.feature_parking_spots.data.datasource.dto.AddressInfo

data class ParkingSpotModel(
    val AddressInfo: AddressInfo,
    val ID: Int,
    val NumberOfPoints: Int,
)
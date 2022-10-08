package com.mycariadtask.feature_parking_spots.presentation

import com.mycariadtask.feature_parking_spots.domain.model.ParkingSpotModel

data class ParkingSpotState(
    val isLoading: Boolean = false,
    val parkingSportList: List<ParkingSpotModel> = emptyList(),
    val error: String = ""
)

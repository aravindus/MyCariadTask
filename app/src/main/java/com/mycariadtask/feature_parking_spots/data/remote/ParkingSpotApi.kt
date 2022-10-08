package com.mycariadtask.feature_parking_spots.data.remote

import com.mycariadtask.feature_parking_spots.data.datasource.dto.ResponseParkingSpotItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ParkingSpotApi {
    @GET("/v3/poi/?")

    suspend fun getParkingSpot(
        @Query("key") key: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("distance") distance: Int,
        @Query("maxresults") maxresults: Int,
    ): List<ResponseParkingSpotItemDto>
}
package com.mycariadtask.feature_parking_spots.domain.model

data class AddressInfoModel(
    val AccessComments: String,
    val AddressLine1: String,
    val AddressLine2: String,
    val ID: Int,
    val Distance: Double,
    val Latitude: Double,
    val Longitude: Double,
    val Title: String,
    val Town: String
)

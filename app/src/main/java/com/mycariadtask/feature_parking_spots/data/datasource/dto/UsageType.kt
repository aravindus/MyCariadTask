package com.mycariadtask.feature_parking_spots.data.datasource.dto

data class UsageType(
    val ID: Int,
    val IsAccessKeyRequired: Boolean,
    val IsMembershipRequired: Boolean,
    val IsPayAtLocation: Boolean,
    val Title: String
)
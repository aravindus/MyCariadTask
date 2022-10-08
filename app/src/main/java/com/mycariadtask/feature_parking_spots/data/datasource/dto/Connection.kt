package com.mycariadtask.feature_parking_spots.data.datasource.dto

data class Connection(
    val Amps: Int,
    val Comments: Any,
    val ConnectionType: ConnectionType,
    val ConnectionTypeID: Int,
    val CurrentType: CurrentType,
    val CurrentTypeID: Int,
    val ID: Int,
    val Level: Level,
    val LevelID: Int,
    val PowerKW: Double,
    val Quantity: Int,
    val Reference: Any,
    val StatusType: StatusTypeX,
    val StatusTypeID: Int,
    val Voltage: Int
)
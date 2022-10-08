package com.mycariadtask.feature_parking_spots.data.datasource.dto

import com.mycariadtask.feature_parking_spots.domain.model.AddressInfoModel

data class AddressInfo(
    val AccessComments: String,
    val AddressLine1: String,
    val AddressLine2: String,
    val ContactEmail: Any,
    val ContactTelephone1: String,
    val ContactTelephone2: Any,
    val Country: Country,
    val CountryID: Int,
    val Distance: Double,
    val DistanceUnit: Int,
    val ID: Int,
    val Latitude: Double,
    val Longitude: Double,
    val Postcode: String,
    val RelatedURL: String,
    val StateOrProvince: String,
    val Title: String,
    val Town: String
)

fun AddressInfo.toResponseAddressInfo(): AddressInfoModel {
    return AddressInfoModel(
        AccessComments = AccessComments,
        AddressLine1 = AddressLine1,
        AddressLine2 = AddressLine2,
        ID = ID,
        Distance = Distance,
        Latitude = Latitude,
        Longitude = Longitude,
        Title = Title,
        Town = Town
    )
}
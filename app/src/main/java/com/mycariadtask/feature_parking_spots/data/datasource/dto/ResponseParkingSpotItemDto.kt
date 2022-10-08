package com.mycariadtask.feature_parking_spots.data.datasource.dto

import com.mycariadtask.feature_parking_spots.domain.model.ParkingSpotModel

data class ResponseParkingSpotItemDto(
    val AddressInfo: AddressInfo,
    val Connections: List<Connection>,
    val DataProvider: DataProvider,
    val DataProviderID: Int,
    val DataProvidersReference: String,
    val DataQualityLevel: Int,
    val DateCreated: String,
    val DateLastConfirmed: Any,
    val DateLastStatusUpdate: String,
    val DateLastVerified: String,
    val DatePlanned: Any,
    val GeneralComments: String,
    val ID: Int,
    val IsRecentlyVerified: Boolean,
    val MediaItems: List<MediaItem>,
    val MetadataValues: List<MetadataValue>,
    val NumberOfPoints: Int,
    val OperatorID: Int,
    val OperatorInfo: OperatorInfo,
    val OperatorsReference: String,
    val ParentChargePointID: Any,
    val PercentageSimilarity: Any,
    val StatusType: StatusTypeX,
    val StatusTypeID: Int,
    val SubmissionStatus: SubmissionStatus,
    val SubmissionStatusTypeID: Int,
    val UUID: String,
    val UsageCost: String,
    val UsageType: UsageType,
    val UsageTypeID: Int,
    val UserComments: Any
)


fun ResponseParkingSpotItemDto.toResponseParkingSpotItem(): ParkingSpotModel {
    return ParkingSpotModel(
        AddressInfo = AddressInfo,
        ID = ID,
        NumberOfPoints = NumberOfPoints
    )
}

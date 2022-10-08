package com.mycariadtask.feature_parking_spots.domain.use_case

import android.util.Log
import com.mycariadtask.core.Resource
import com.mycariadtask.feature_parking_spots.data.datasource.dto.toResponseParkingSpotItem
import com.mycariadtask.feature_parking_spots.domain.model.ParkingSpotModel
import com.mycariadtask.feature_parking_spots.domain.repository.ParkingSpotDomainRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetParkingSpotUseCase @Inject constructor(private val repository: ParkingSpotDomainRepository) {
    operator fun invoke(): Flow<Resource<List<ParkingSpotModel>>> = flow {
        while (currentCoroutineContext().isActive) {
            Log.e("TESTING", "invoke: repository.getParkingSpots()")
            try {
                emit(Resource.Loading())
                val parkingSpot =
                    repository.getParkingSpots().map { it.toResponseParkingSpotItem() }
                emit(Resource.Success(parkingSpot))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurs"))
            } catch (e: IOException) {
                emit(Resource.Error("Could not reach the server"))
            }
            delay(30000)
        }
    }
}
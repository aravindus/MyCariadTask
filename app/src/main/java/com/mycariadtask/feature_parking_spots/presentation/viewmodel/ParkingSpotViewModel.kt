package com.mycariadtask.feature_parking_spots.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycariadtask.core.Constants
import com.mycariadtask.core.Resource
import com.mycariadtask.feature_parking_spots.data.remote.ParkingSpotApi
import com.mycariadtask.feature_parking_spots.domain.model.ParkingSpotModel
import com.mycariadtask.feature_parking_spots.domain.use_case.GetParkingSpotUseCase
import com.mycariadtask.feature_parking_spots.presentation.ParkingSpotState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ParkingSpotViewModel @Inject constructor(
    val parkingSpotUseCase: GetParkingSpotUseCase,
    private val parkingSpotApi: ParkingSpotApi
) :
    ViewModel() {
    private val _parkingSpots = MutableLiveData<ParkingSpotState>()
    val parkingSpots: LiveData<ParkingSpotState> = _parkingSpots

    init {
        getParkingSpot()
    }

    fun getParkingSpot() {
        parkingSpotUseCase().onEach {  result ->
            when (result) {
                is Resource.Success -> {
                    _parkingSpots.value =
                        ParkingSpotState(parkingSportList = result.data ?: emptyList())
                }
                is Resource.Error -> {

                    ParkingSpotState(error = result.message ?: "An un expected error occured")
                }
                is Resource.Loading -> {
                    ParkingSpotState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}




package com.mycariadtask.feature_parking_spots.presentation


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mycariadtask.R
import com.mycariadtask.core.Constants
import com.mycariadtask.core.internet.ConnectionLiveData
import com.mycariadtask.databinding.ActivityMapsBinding
import com.mycariadtask.feature_parking_spots.domain.model.ParkingSpotModel
import com.mycariadtask.feature_parking_spots.presentation.viewmodel.ParkingSpotViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val parkingSpotViewModel: ParkingSpotViewModel by viewModels()
    private var zoomLevel = 16.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        networkConnection()
        parkingViewModel()
    }

    private fun parkingViewModel() {
        parkingSpotViewModel.parkingSpots.observe(this) { result ->
            if (result.isLoading) {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("LOADING....")
                dialog.show()
            } else if (result.parkingSportList.isNotEmpty()) {
                getMarkerToMap(result.parkingSportList)

            }
        }
    }

    private fun networkConnection() {
        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            if (!isNetworkAvailable) {
                Log.e("ERROR", "onCreate:Connection Lost ")
                Toast.makeText(
                    this,
                    "Connection Lost, No real time data is available",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e("ERROR", "onCreate:Connection Lost Good Connection")
                Toast.makeText(this, "Good Connection, data updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(Constants.Latitude, Constants.Longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("You are here"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))

    }

    private fun getMarkerToMap(parkingSpotResponse: List<ParkingSpotModel>) {
        for (i in parkingSpotResponse.indices) {
            val sydney = LatLng(
                parkingSpotResponse[i].AddressInfo.Latitude,
                parkingSpotResponse[i].AddressInfo.Longitude
            )
            mMap.addMarker(
                MarkerOptions().position(sydney)
                    .title("You are here" + parkingSpotResponse[i].AddressInfo.Title)
            )
            mMap.setOnMarkerClickListener {
                val dialog = Dialog(this)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.dialog_parking_details)
                val textPorts = dialog.findViewById(R.id.text_ports) as TextView
                val titleParking = dialog.findViewById(R.id.title_parking) as TextView
                val textChargingAddress =
                    dialog.findViewById(R.id.text_charging_address) as TextView
                val btnDrive = dialog.findViewById(R.id.btn_drive) as Button
                val btnCancel = dialog.findViewById(R.id.btn_cancel) as TextView
                val window = dialog.window
                window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.CENTER)
                if (parkingSpotResponse[i].AddressInfo.Title.isNotEmpty()) {
                    titleParking.text = parkingSpotResponse[i].AddressInfo.Title
                }

                if (parkingSpotResponse[i].AddressInfo.AddressLine1.isNotEmpty() &&
                    !parkingSpotResponse[i].AddressInfo.AddressLine2.isNullOrBlank()
                ) {
                    textChargingAddress.text =
                        parkingSpotResponse[i].AddressInfo.AddressLine1 + ", " + parkingSpotResponse[i].AddressInfo.AddressLine2
                } else textChargingAddress.text =
                    parkingSpotResponse[i].AddressInfo.AddressLine1
                if (parkingSpotResponse[i].NumberOfPoints.toString().isNotEmpty()) {
                    textPorts.text = parkingSpotResponse[i].NumberOfPoints.toString()
                }

                btnDrive.setOnClickListener {
                    Toast.makeText(
                        baseContext,
                        "This function is not available now",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }
                btnCancel.setOnClickListener { dialog.dismiss() }
                dialog.show()
                false
            }
        }
    }
}
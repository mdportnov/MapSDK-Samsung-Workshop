package com.example.mapsdk

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.mapsdk.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {
    lateinit var mapFragment: SupportMapFragment
    lateinit var binding: FragmentMapBinding
    lateinit var args: OrdersFragmentArgs
    lateinit var locationManager: LocationManager

    private val orders: List<Order> by lazy {
        OrdersReader(requireContext()).read()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater)

//        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager.requestLocationUpdates(
//            LocationManager.NETWORK_PROVIDER,
//            MIN_TIME,
//            MIN_DISTANCE,
//            this
//        );

        mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment

        mapFragment.getMapAsync { gm ->
            gm.isMyLocationEnabled = true
        }

        arguments?.let {
            args = OrdersFragmentArgs.fromBundle(it)
        }

        if (!this::args.isInitialized)
            mapFragment.getMapAsync { googleMap ->
                addAllMarkers(googleMap)
                googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))
            }
        else
            mapFragment.getMapAsync { googleMap ->
                orders.find { it.name == args.orderName }?.let {
                    googleMap.addMarker(
                        MarkerOptions()
                            .title(it.name)
                            .position(it.geometry.location.toGmsLatLng)
                    ).apply { tag = it }
                    googleMap.apply {
                        setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))
                        moveCamera(CameraUpdateFactory.zoomTo(17.0f))
                        moveCamera(
                            CameraUpdateFactory.newLatLng(it.geometry.location.toGmsLatLng)
                        )
                    }
                }
            }

        return binding.root
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    private fun addAllMarkers(googleMap: GoogleMap) {
        orders.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.geometry.location.toGmsLatLng)
            ).apply { tag = place }
        }
    }
}
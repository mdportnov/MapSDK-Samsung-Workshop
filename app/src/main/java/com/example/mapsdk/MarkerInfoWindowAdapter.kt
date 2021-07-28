package com.example.mapsdk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.mapsdk.databinding.MarkerInfoContentsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker): View? {
        // Return null to indicate that the
        // default window (white bubble) should be used
        return null
    }

    override fun getInfoContents(marker: Marker): View? {
        val order = marker.tag as? Order ?: return null
        val binding = MarkerInfoContentsBinding.inflate(LayoutInflater.from(context))

        binding.textViewTitle.text = order.name
        binding.textViewAddress.text = order.address
        binding.textViewRating.text = "Заказ: " + order.content
        return binding.root
    }
}
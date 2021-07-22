package com.example.mapsdk

import com.google.maps.model.Geometry
import com.google.maps.model.LatLng


val LatLng.toGmsLatLng: com.google.android.gms.maps.model.LatLng
    get() {
        return com.google.android.gms.maps.model.LatLng(this.lat, this.lng)
    }

class Order(
    val geometry: Geometry,
    val name: String,
    val address: String,
    val content: String
)


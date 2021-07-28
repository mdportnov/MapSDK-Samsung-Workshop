package com.example.mapsdk

import com.google.android.gms.maps.model.LatLng


class Order(
    val latLng: LatLng,
    val name: String,
    val address: String,
    val content: String
)


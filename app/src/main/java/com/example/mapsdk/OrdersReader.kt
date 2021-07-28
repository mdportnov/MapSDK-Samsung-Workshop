package com.example.mapsdk

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.apache.commons.io.IOUtils
import java.io.InputStream

class OrdersReader(private val context: Context) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type = Types.newParameterizedType(
        List::class.java,
        Order::class.java,
        LatLng::class.java
    )

    private val ordersAdapter = moshi.adapter<List<Order>>(type)

    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.orders)

    fun read(): List<Order>? {
        return ordersAdapter.fromJson(IOUtils.toString(inputStream)).also {
            IOUtils.closeQuietly(inputStream)
        }
    }
}
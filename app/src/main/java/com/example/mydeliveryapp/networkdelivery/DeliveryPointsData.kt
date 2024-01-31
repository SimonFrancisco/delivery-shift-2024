package com.example.mydeliveryapp.networkdelivery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable

@Parcelize
data class DeliveryPoints(
    val points: @RawValue List<Point>,
    val success: Boolean
) : Serializable, Parcelable

@Parcelize
data class Point(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String
) : Parcelable


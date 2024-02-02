package com.example.mydeliveryapp.networkdelivery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable

@Parcelize
data class DeliveryTypes(
    val packages: @RawValue List<PackageType>,
    val success: Boolean
) : Parcelable, Serializable

@Parcelize
data class PackageType(
    val height: Int,
    val id: String,
    val length: Int,
    val name: String,
    val weight: Int,
    val width: Int
) : Parcelable
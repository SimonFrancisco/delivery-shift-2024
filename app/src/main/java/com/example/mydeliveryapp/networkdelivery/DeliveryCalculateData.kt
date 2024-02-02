package com.example.mydeliveryapp.networkdelivery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable
@Parcelize
data class DeliveryCalculateList(
    val options: List<Option>,
    val success: Boolean
):Parcelable
@Parcelize
data class Option(
    val days: Int,
    val id: String,
    val name: String,
    val price: Int,
    val type: String
):Parcelable
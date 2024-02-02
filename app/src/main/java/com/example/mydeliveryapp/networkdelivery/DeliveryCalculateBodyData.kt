package com.example.mydeliveryapp.networkdelivery

import com.squareup.moshi.Json

data class CalculateBody(
    @Json(name = "package")val packageDetails: Package,
    val senderPoint: SenderPoint,
    val receiverPoint: ReceiverPoint
)
data class Package (
    val length: String,
    val height: String,
    val weight: String,
    val width: String
)
data class ReceiverPoint(
    val latitude: String,
    val longitude: String
)
data class SenderPoint(
    val latitude: String,
    val longitude: String
)

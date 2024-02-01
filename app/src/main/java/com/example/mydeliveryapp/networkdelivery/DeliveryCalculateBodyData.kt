package com.example.mydeliveryapp.networkdelivery

data class CalculateBody(
    val packageDetails: PackageDetails,
    val receiverPoint: ReceiverPoint,
    val senderPoint: SenderPoint
)
data class PackageDetails (
    val height: String,
    val length: String,
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
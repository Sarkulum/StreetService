package com.example.SmartSteuer.application.OpenPLZ

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val name: String,  // street name
    val locality: String,
)

data class StreetInfo(
    val outputStreet: String,
    val outputLocality: String
)


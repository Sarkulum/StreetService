package com.example.SmartSteuer.application.OpenPLZ

import kotlinx.serialization.Serializable

    @Serializable
    data class LocationResponse(
        val name: String,  // street name
        val postalCode: String,
        val locality: String,
        val borough: String? = null,  // Optional
        val suburb: String? = null,  // Optional
        val municipality: Municipality,
        val district: District? = null,  // Optional
        val federalState: FederalState
    )

    // Municipality data class (kommunal)
    @Serializable
    data class Municipality(
        val key: String,
        val name: String,
        val type: String
    )

    // District data class
    @Serializable
    data class District(
        val key: String,
        val name: String,
        val type: String
    )

    // State data class
    @Serializable
    data class FederalState(
        val key: String,
        val name: String
    )

    data class StreetInfo(
        val outputStreet: String,
        val outputLocality: String
    )


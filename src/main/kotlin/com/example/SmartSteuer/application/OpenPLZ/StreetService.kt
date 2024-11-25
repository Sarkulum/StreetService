package com.example.SmartSteuer.application.OpenPLZ

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import kotlinx.serialization.json.*
import org.springframework.stereotype.Service
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

@Service
class StreetService() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun requestToOpenPLZ(name: String?, plz: String?, locality: String?): Any {

        if (name.isNullOrEmpty() && plz.isNullOrEmpty() && locality.isNullOrEmpty()){
            return "No street with matching params found"
        }

        try {
            val validStreet = name?.encodeURLParameter()

            val responseBody: List<LocationResponse> =
                client.get("https://openplzapi.org/de/Streets?name=$validStreet&postalCode=$plz&locality=$locality")
                    .body()


            val streetInfoList = responseBody.map { location ->
                val outputStreet = location.name
                val outputLocality = location.locality
                val outputPostalCode = location.postalCode
                StreetInfo(outputStreet, outputLocality, outputPostalCode,)
            }
            return streetInfoList
        } catch (e: Exception) {
            return "Error while fetching data: ${e.message}"
        }
    }
}
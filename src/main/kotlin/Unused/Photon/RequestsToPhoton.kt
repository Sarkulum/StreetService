package Unused.Photon

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

suspend fun RequestToPhoton(street: String): List<String> {
    // Create a Ktor client
    val client = HttpClient(CIO)
    val url = Url("https://photon.komoot.io/api/?q=$street")


        // GET request
        val response: HttpResponse = client.get(url)

        // Convert to raw JSON string
        val jsonString = response.bodyAsText()

        // raw JSON string into a JsonObject
        val jsonObject = Json.parseToJsonElement(jsonString).jsonObject

        client.close()

        // Extract streets from response
        val outputStreets = mutableListOf<String>()
        val features = jsonObject["features"]?.jsonArray

        features?.forEach { feature ->
            val properties = feature.jsonObject["properties"]?.jsonObject
            val street = properties?.get("street")?.jsonPrimitive?.content
            if (street != null) {
                outputStreets.add(street)
            }
        }

    return outputStreets
}

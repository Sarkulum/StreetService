import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.*  // For working with JsonObject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.Scanner

// Function to fetch streets from Photon API based on a query
suspend fun fetchStreets(query: String): List<String> {
    val client = HttpClient(CIO)

    // Perform the GET request to Photon API
    val url = "https://photon.komoot.io/api/?q=$query"
    val response: HttpResponse = client.get(url)

    // Convert the response body to a raw JSON string
    val jsonString = response.bodyAsText()

    // Parse the raw JSON string into a JsonObject using kotlinx.serialization
    val jsonObject = Json.parseToJsonElement(jsonString).jsonObject

    client.close()

    // Extract all streets from the response
    val streets = mutableListOf<String>()
    val features = jsonObject["features"]?.jsonArray

    features?.forEach { feature ->
        val properties = feature.jsonObject["properties"]?.jsonObject
        val street = properties?.get("street")?.jsonPrimitive?.content
        if (street != null) {
            streets.add(street)
        }
    }

    return streets
}

fun main() = runBlocking {
    // Create a scanner to read user input
    val scanner = Scanner(System.`in`)

    // Prompt user for a query (e.g., a city or area)
    println("Enter a search term (e.g., city or area): ")
    val query = scanner.nextLine()

    // Fetch the streets for the query
    val streets = fetchStreets(query)

    // Print the extracted streets
    if (streets.isNotEmpty()) {
        println("Found streets:")
        streets.forEach { street ->
            println(street)
        }
    } else {
        println("No streets found.")
    }

    // Close the scanner
    scanner.close()
}

package Unused.Tests

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.call.*
import io.ktor.http.*

suspend fun main() {
    // Create a Ktor client
    val client = HttpClient(CIO) // CIO engine, for non-blocking requests

    try {
        // Make a GET request
        val response = client.get("photon.komoot.io/api/?q=berlin")
        val responseBody = response.toString()
        println(responseBody)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        // Close the client
        client.close()
    }
}

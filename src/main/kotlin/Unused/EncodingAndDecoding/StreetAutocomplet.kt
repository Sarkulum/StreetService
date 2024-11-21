package Unused.EncodingAndDecoding

import com.example.SmartSteuer.application.Photon.RequestToPhoton
import kotlinx.coroutines.runBlocking


class StreetAutocomplet() {
    fun Calls(street: String) = runBlocking {
        var outputStreets = RequestToPhoton(street)

        return@runBlocking outputStreets
    }
}


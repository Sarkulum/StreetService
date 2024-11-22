package Unused.EncodingAndDecoding

import Unused.Photon.RequestToPhoton
import kotlinx.coroutines.runBlocking


class StreetAutocomplet() {
    fun Calls(street: String) = runBlocking {
        var outputStreets = RequestToPhoton(street)

        return@runBlocking outputStreets
    }
}


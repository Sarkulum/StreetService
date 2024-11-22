package com.example.SmartSteuer.application.OpenPLZ

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/OpenPLZ")
class StreetController(
    @Qualifier("streetService") private val service: StreetService
){
    @GetMapping("/streets")
    fun getStreetsWithQueryParams(
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "plz", required = false) plz: String?,
        @RequestParam(name = "locality", required = false) locality: String?
    )= runBlocking {
        val outputStreets = service.requestToOpenPLZ(name, plz, locality)
        return@runBlocking outputStreets
    }
    // Url format looks like this /api/OpenPLZ/streets?name="SomeNameHere"&plz="SomePLZHere"&locality="SomeLocalityHere"
}
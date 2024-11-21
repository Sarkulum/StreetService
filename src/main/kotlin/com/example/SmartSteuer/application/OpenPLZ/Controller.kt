package com.example.SmartSteuer.application.OpenPLZ

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller(
    @Qualifier("streetService") private val service: StreetService
){
    @GetMapping("/api/OpenPLZ/streets/{plz}")
    fun getStreetsWithPLZ(@PathVariable plz: String)= runBlocking{
        val outputStreets = service.requestToOpenPLZ(plz)
        return@runBlocking outputStreets
    }

    @GetMapping("/api/OpenPLZ/fullText/{input}")
    fun getStreetsWithText(@PathVariable input: String) = runBlocking {
        val outputStreets = service.requestUseingText(input)
        return@runBlocking outputStreets
    }

}
package Unused.EncodingAndDecoding

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

fun Decoding(outputJson: JsonObject): String? {
    val properties = outputJson["features"]?.jsonArray?.get(0)?.jsonObject?.get("properties")?.jsonObject
    val street = properties?.get("street")?.jsonPrimitive?.content

    return street
}
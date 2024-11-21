package Unused.EncodingAndDecoding

fun EncodingToUrlPhoton(street: String): String{
    var output: String = "photon.komoot.io/api/?q$street"
    return output
}
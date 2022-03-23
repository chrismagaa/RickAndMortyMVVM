package com.christian_magana.rickAndMortyMVVM.data.model

class QueryRAM(
    var name: String = "",
    var status: String = "",
    var gender: String = "",
    var filters: Array<Int> = arrayOf(0,0)
)

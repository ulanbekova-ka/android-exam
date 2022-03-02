package com.kay.prog.exam.api

data class Item(
    val id: Long,
    var name: String,
    val status: String,
    val species: String,
    val location: List<String>,
    val image: String?
)
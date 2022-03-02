package com.kay.prog.exam.database

import androidx.room.Entity

@Entity
data class Character(
    val id: Long,
    var name: String,
    val status: String,
    val species: String,
    val location: List<String>,
    val image: String?
)

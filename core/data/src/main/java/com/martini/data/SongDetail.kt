package com.martini.data

data class SongDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageURL: String,
    val residents: List<Resident>
)

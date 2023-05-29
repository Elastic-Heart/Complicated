package com.martini.complicated.details

import com.martini.data.Resident

data class SongDetails(
    val id: Long,
    val name: String,
    val description: String,
    val imageURL: String,
    val residents: List<Resident>
)

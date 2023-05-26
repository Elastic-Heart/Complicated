package com.example.complicated.data

interface CondoUnitFilter {
    suspend fun filter(text: CharSequence?, units: List<CondoUnit>): List<CondoUnit>
}
package com.example.complicated.data

class CondoNameFilter : CondoUnitFilter {
    override suspend fun filter(text: CharSequence?, units: List<CondoUnit>): List<CondoUnit> {
        if (text.isNullOrEmpty()) return units

        val query = text.toString().lowercase()

        return units.filter { it.name.lowercase().contains(query) }
    }
}
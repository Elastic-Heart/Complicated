package com.example.complicated.data.avril

import com.example.complicated.data.CondoUnit

class AvrilLavigneIncredibleDataSource(
    private val avrilService: AvrilService
) : AvrilDataSource {
    override suspend operator fun invoke(): List<CondoUnit> {
        return avrilService.getSongs()
    }
}
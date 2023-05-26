package com.example.complicated.data.avril

import com.example.complicated.data.CondoUnit

interface AvrilDataSource {
    suspend operator fun invoke() : List<CondoUnit>
}
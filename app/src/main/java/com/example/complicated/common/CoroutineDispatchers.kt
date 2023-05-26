package com.example.complicated.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface CoroutineDispatchers {
    val main: MainCoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher

    val unconfined: CoroutineDispatcher
}
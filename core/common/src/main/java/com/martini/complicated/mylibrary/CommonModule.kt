package com.martini.complicated.mylibrary

import org.koin.dsl.module

val commonModule = module {
    factory<CoroutineDispatchers> { AppCoroutineDispatchers() }
}
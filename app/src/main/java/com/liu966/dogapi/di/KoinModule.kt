package com.liu966.dogapi.di

import com.liu966.dogapi.data.APIClient
import org.koin.dsl.module

val appModule = module {
    single { APIClient.getClient() }
}
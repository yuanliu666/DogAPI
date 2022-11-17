package com.liu966.dogapi.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {


    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"
        private lateinit var retrofit: Retrofit

        fun getClient(): Retrofit {
            if (::retrofit.isInitialized) {
                return retrofit
            }
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}
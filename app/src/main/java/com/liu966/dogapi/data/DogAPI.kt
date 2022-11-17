package com.liu966.dogapi.data

import retrofit2.Response
import retrofit2.http.GET

interface DogAPI {
    @GET("breeds/image/random")
    suspend fun getRandomSingleDogImage(): Response<RandomSingleDogImageResponse>
}
package com.imams.animals.source.remote.api

import com.imams.animals.source.remote.model.AnimalsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimalApi {

    @GET("animals")
    suspend fun getAnimals(
        @Query("name") name: String
    ): List<AnimalsResponse>

}
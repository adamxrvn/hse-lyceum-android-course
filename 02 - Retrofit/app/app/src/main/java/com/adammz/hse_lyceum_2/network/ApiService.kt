package com.adammz.hse_lyceum_2.network

import com.adammz.hse_lyceum_2.model.Sandwich
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/sandwiches")
    suspend fun getSandwiches(): List<Sandwich>

    @POST("/sandwiches")
    suspend fun addSandwich(@Body sandwich: Sandwich): Sandwich

    @PUT("/sandwiches/{id}")
    suspend fun updateSandwich(@Path("id") id: Int): Sandwich

    @DELETE("/sandwiches/{id}")
    suspend fun deleteSandwich(@Path("id") id: Int)
}



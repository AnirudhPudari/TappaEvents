package com.eazybytes.data.api

//import com.example.domain.model.Coin
import com.eazybytes.data.model.EventModel
import retrofit2.http.GET

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<EventModel>
}
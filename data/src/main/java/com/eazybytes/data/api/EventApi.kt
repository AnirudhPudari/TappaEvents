package com.eazybytes.data.api

//import com.example.domain.model.Coin
import com.eazybytes.data.model.EventModel
import retrofit2.http.GET

interface EventApi {
    @GET("c03ef38975ef65cb6b95")
    suspend fun getEvents(): List<EventModel>
}
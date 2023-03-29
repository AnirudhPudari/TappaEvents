package com.eazybytes.domain.repository

import com.eazybytes.domain.model.EventItem

interface EventRepository {
    suspend fun getEvents(): List<EventItem>
}
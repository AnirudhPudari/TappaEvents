package com.eazybytes.data.repository

import com.eazybytes.data.api.EventApi
import com.eazybytes.data.extensions.toCoin
import com.eazybytes.domain.model.EventItem
import com.eazybytes.domain.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val api: EventApi
): EventRepository {
    override suspend fun getEvents(): List<EventItem> {
        try {
            return api.getEvents().map { it.toCoin() }
        } catch (e: Exception) {
            throw e
        }
    }
}
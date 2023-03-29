package com.eazybytes.tappaevents.model

import com.eazybytes.domain.model.EventItem

data class EventListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val eventItems: List<EventItem> = emptyList()
)

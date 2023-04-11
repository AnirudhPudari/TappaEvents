package com.eazybytes.domain.model

data class EventItem(
    val dateString: String,
    val eventTitle: String,
    val eventHost: String,
    val eventLocation: String,
    val eventDateTime: String,
    val eventCounty: String,
    val posterThumb: String,
    val posterTUrl: String,
)

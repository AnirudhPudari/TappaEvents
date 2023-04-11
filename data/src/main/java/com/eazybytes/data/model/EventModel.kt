package com.eazybytes.data.model

import com.google.gson.annotations.SerializedName

data class EventModel(
    val dateString: String,
    val eventTitle: String,
    val eventHost: String,
    val eventLocation: String,
    val eventDateTime: String,
    val eventCounty: String,
    val posterThumb: String,
    val posterTUrl: String,
)

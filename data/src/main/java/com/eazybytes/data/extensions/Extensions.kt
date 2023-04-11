package com.eazybytes.data.extensions

import com.eazybytes.data.model.EventModel
import com.eazybytes.domain.model.EventItem

fun EventModel.eventItem(): EventItem {
    return EventItem(
        dateString = dateString,
        eventTitle = eventTitle,
        eventHost = eventHost,
        eventLocation = eventLocation,
        eventDateTime = eventDateTime,
        eventCounty = eventCounty,
        posterThumb = posterThumb,
        posterTUrl = posterTUrl,
    )
}
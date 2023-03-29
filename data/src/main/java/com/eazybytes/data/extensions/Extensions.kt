package com.eazybytes.data.extensions

import com.eazybytes.data.model.EventModel
import com.eazybytes.domain.model.EventItem

fun EventModel.toCoin(): EventItem {
    return EventItem(
        src = src,
        description = description
    )
}
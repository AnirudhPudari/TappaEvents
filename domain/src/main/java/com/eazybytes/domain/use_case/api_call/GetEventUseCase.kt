package com.eazybytes.domain.use_case.api_call

import com.eazybytes.domain.common.Resource
import com.eazybytes.domain.model.EventItem
import com.eazybytes.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(): Flow<Resource<List<EventItem>>> = flow {
//        emit(Resource.Loading())
        try {
            emit(Resource.Loading())
            val eventItems = repository.getEvents()
            emit(Resource.Success(eventItems))
        } catch (e : IOException) {
//            emit(Resource.Error("Error: ${e.message}"))
            e.printStackTrace()

            when {
                e.message?.contains("4") == true -> {
                    emit(Resource.Error("Check Your Internet Connection"))
                }
                else -> {
                    emit(Resource.Error("Something Went Wrong"))
                }
            }
        }
    }
}
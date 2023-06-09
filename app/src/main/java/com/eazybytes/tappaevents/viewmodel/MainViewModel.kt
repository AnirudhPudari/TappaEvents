package com.eazybytes.tappaevents.viewmodel

import androidx.lifecycle.*
import com.eazybytes.tappaevents.model.EventListState
import com.eazybytes.domain.common.Resource
import com.eazybytes.domain.use_case.api_call.GetEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEventUseCase: GetEventUseCase,
): ViewModel() {
    private val _state = MutableLiveData<EventListState>()
    val state: LiveData<EventListState> = _state


    init {
        getEvents()
    }

    fun getEvents() {
        getEventUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = EventListState(eventItems = result.data ?: emptyList(), error = "")

                    println("${_state.value}")
                }
                is Resource.Error -> {
                    _state.value = EventListState(error = result.message ?: "Error occurred")
                }
                is Resource.Loading -> {
                    _state.value = EventListState(isLoading = true, error = "")
                }
            }
        }.launchIn(viewModelScope)
    }
}
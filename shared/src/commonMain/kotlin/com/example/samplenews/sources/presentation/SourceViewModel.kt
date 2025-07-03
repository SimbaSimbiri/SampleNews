package com.example.samplenews.sources.presentation

import com.example.samplenews.BaseViewModel
import com.example.samplenews.sources.application.SourceUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourceViewModel(private val sourceUseCase: SourceUseCase) : BaseViewModel() {

    private val _sourceStateFlow: MutableStateFlow<SourceState> =
        MutableStateFlow(SourceState.LoadingInitial)

    val sourceStateFlow: StateFlow<SourceState> get() = _sourceStateFlow

    init {
        getSources()
    }

    fun getSources(forceFetch: Boolean = false) {
        scope.launch {
            val currentState = _sourceStateFlow.value

            if (currentState is SourceState.Success && forceFetch) {
                _sourceStateFlow.emit(SourceState.Refreshing(currentState.sources))
                delay(600)
                _sourceStateFlow.emit(SourceState.LoadingInitial)
                delay(600)
            } else if (currentState !is SourceState.Success) {
                _sourceStateFlow.emit(SourceState.LoadingInitial)
            }

            try {
                val fetched = sourceUseCase.getSources(forceFetch)

                if (fetched.isNotEmpty()) {
                    _sourceStateFlow.emit(SourceState.Success(fetched))
                } else {
                    _sourceStateFlow.emit(SourceState.Empty)
                }
            } catch (e: Exception) {
                _sourceStateFlow.emit(
                    SourceState.Error(
                        e.message ?: "Something went wrong. Try again later"
                    )
                )
            }

        }
    }
}
package com.example.samplenews.sources.presentation

import com.example.samplenews.BaseViewModel
import com.example.samplenews.sources.application.Source
import com.example.samplenews.sources.application.SourceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourceViewModel(private val sourceUseCase: SourceUseCase) : BaseViewModel() {

    private val _sourceStateFlow : MutableStateFlow<SourceState> =
        MutableStateFlow(SourceState.Loading)

    val sourceStateFlow: StateFlow<SourceState> get() = _sourceStateFlow

    init {
        getSources()
    }

    private fun getSources() {
        scope.launch {
            val fetchedSources: List<Source> = sourceUseCase.getSources()

            if (fetchedSources.isNotEmpty()) {
                _sourceStateFlow.emit(SourceState.Success(sources = fetchedSources))
            }
            else _sourceStateFlow.emit(SourceState.Empty)

        }
    }
}
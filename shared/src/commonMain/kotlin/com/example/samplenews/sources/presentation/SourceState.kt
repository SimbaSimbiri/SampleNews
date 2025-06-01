package com.example.samplenews.sources.presentation

import com.example.samplenews.sources.application.Source


sealed class SourceState {
    data object LoadingInitial: SourceState()
    data class Refreshing(val sources: List<Source>): SourceState()
    data class Success(val sources: List<Source>): SourceState()
    data class Error(val message: String): SourceState()
    data object Empty: SourceState()

}
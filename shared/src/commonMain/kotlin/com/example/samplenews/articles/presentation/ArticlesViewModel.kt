package com.example.samplenews.articles.presentation

import com.example.samplenews.BaseViewModel
import com.example.samplenews.articles.application.ArticleUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticleUseCase
) : BaseViewModel() {

    // this single stream of stateflow will expose every bit of info that our UI needs and we can
    // keep the UI as dumb as possible
    private val _articleStateFlow: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState.LoadingInitial)
    // our first state is the LoadingInitial state that triggers the shimmer UI

    // our public stream should be immutable so that no external intrusion can change the state
    val articleStateFlow: StateFlow<ArticleState> get() = _articleStateFlow

    init {
        // instead of having our UI call this logic function, we just call it here so that when
        // the UI instantiates our viewModel via Koin DI, it will immediately subscribe to the
        // articleStateFlow
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            val currentState = _articleStateFlow.value

            if (currentState is ArticleState.Success && forceFetch) { // user wants fresh articles

                // we initiate a force Refresh state that will enable showing the refresh loader
                // behind the scenes, we will call a fetch from the backend
                _articleStateFlow.emit(ArticleState.Refreshing(currentState.articles))
                delay(400)
                _articleStateFlow.emit(ArticleState.LoadingInitial)
                delay(400)
                // while refreshing i.e retrieving fresh articles, we still want to display the
                // previously loaded articles so we parse in that list
            } else if (currentState !is ArticleState.Success) { // it is the first time the user
                // has logged in the app so just do the initial fetch and display the shimmer list
                _articleStateFlow.emit(ArticleState.LoadingInitial)
            }

            try {
                val fetched = useCase.getArticles(forceFetch)

                if (fetched.isNotEmpty()) {
                    _articleStateFlow.emit(ArticleState.Success(fetched))
                } else {
                    _articleStateFlow.emit(ArticleState.Empty)
                }
            } catch (e: Exception) {
                _articleStateFlow.emit(
                    ArticleState.Error(
                        e.message ?: "Something went wrong. Try again later"
                    )
                )
            }
        }
    }

}
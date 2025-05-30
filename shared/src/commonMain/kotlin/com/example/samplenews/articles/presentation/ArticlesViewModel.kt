package com.example.samplenews.articles.presentation

import com.example.samplenews.BaseViewModel
import com.example.samplenews.articles.application.ArticleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticleUseCase
) : BaseViewModel() {

    // this single stream of stateflow will expose every bit of info that our UI needs and we can
    // keep the UI as dumb as possible
    private val _articleStateFlow: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState.Loading)

    // our public stream should be immutable so that no external intrusion can change the state
    val articleStateFlow: StateFlow<ArticleState> get() =_articleStateFlow

    init {
        // instead of having our UI call this logic function, we just call it here so that when
        // the UI instantiates our viewModel via Koin DI, it will immediately subscribe to the
        // articleStateFlow
        getArticles()
    }

    private fun getArticles(){
        scope.launch {
            // we now call the backend to fetch the articles
            val fetched = useCase.getArticles()

            // this state will be transmitted back to the UI
            if (fetched.isNotEmpty())
                _articleStateFlow.emit(ArticleState.Success(articles = fetched))
            else
                _articleStateFlow.emit(ArticleState.Empty)
        }
    }


}
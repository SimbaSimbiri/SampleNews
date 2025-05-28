package com.example.samplenews.articles

import com.example.samplenews.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel : BaseViewModel() {
    // this single stream of stateflow will expose every bit of info that our UI needs and we can
    // keep the UI as dumb as possible

    private val _articleState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState.Loading)

    // our public stream should be immutable so that no external intrusion can change the state
    val articleState: StateFlow<ArticleState> get() =_articleState
    private val useCase: ArticleUseCase

    init {
        val httpClient = HttpClient{
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val service = ArticlesService(httpClient)
        useCase = ArticleUseCase(service)

        // instead of having our UI call this logic function, we just call it here so that when
        // the UI creates our viewModel, it will immediately subscribe to the articleStateFlow
        getArticles()

    }

    private fun getArticles(){
        scope.launch {
            val fetched = useCase.getArticles() // we now call the backend to fetch the articles

            if (fetched.isEmpty()){
                _articleState.emit(ArticleState.Empty)
            } else{
                _articleState.emit(ArticleState.Success(articles = fetched))
                // this state will be transmitted back to the UI
            }
        }
    }


}
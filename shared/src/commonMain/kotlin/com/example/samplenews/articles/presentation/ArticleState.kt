package com.example.samplenews.articles.presentation

import com.example.samplenews.articles.application.Article

//We would have represented our state with Sealed classes but KMP doesn't support them yet
/*data class ArticleState (
    val articles: List<Article> = listOf(),
    val loading: Boolean =  false,
    val error: String? = null
)*/

sealed class ArticleState{
    data object Loading : ArticleState()
    data class Success(val articles: List<Article>): ArticleState()
    data class Error(val message: String): ArticleState()
    data object Empty: ArticleState()
}

package com.example.samplenews.articles.presentation

import com.example.samplenews.articles.application.Article

sealed class ArticleState{
    data object LoadingInitial : ArticleState() //added for our shimmer effect
    data class Refreshing(val articles: List<Article>) : ArticleState() // added for pull refresh
    data class Success(val articles: List<Article>): ArticleState()
    data class Error(val message: String): ArticleState()
    data object Empty: ArticleState()
}

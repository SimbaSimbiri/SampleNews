package com.example.samplenews.articles.presentation

import com.example.samplenews.articles.application.Article

sealed class ArticleState{
    data object Loading : ArticleState()
    data class Success(val articles: List<Article>): ArticleState()
    data class Error(val message: String): ArticleState()
    data object Empty: ArticleState()
}

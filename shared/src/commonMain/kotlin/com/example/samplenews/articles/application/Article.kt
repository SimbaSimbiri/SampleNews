package com.example.samplenews.articles.application

data class Article(
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String,
    val publisher:String,
    val author: String?,
    val urlToPage: String
)
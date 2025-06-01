package com.example.samplenews.articles.application

data class Article(
    val title: String,
    val description: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val publisher:String
)
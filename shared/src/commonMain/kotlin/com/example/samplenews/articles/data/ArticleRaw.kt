package com.example.samplenews.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRaw (
// we only map the json keys that we need to our data class attributes
    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,

    @SerialName("urlToImage")
    val imageUrl: String?,

    @SerialName("publishedAt")
    val date: String,

    @SerialName("author")
    val author: String?,

    @SerialName("source")
    val source: SourceArt,

    @SerialName("url")
    val urlToPage: String

)
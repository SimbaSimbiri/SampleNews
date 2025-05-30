package com.example.samplenews.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticlesResponse(
    // we map our api response object to the appropriate json keys as they appear in the API results

    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val totalResults: Int,

    @SerialName("articles")
    val articleList: List<ArticleRaw>

)
package com.example.samplenews.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceArt(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String
)

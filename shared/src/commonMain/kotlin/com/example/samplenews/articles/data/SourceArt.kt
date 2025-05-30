package com.example.samplenews.articles.data

import kotlinx.serialization.Serializable

@Serializable
data class SourceArt(
    val id: String? = null,
    val name: String
)

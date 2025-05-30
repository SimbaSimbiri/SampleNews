package com.example.samplenews.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceRaw (
@SerialName("name")
val name: String,

@SerialName("description")
val description: String,

@SerialName("url")
val homepage: String,

@SerialName("country")
val country: String,

@SerialName("language")
val language: String
)
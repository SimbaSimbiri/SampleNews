package com.example.samplenews.sources.data

import com.example.samplenews.Secrets
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourceService(private val httpClient: HttpClient) {

    private val category = "business"
    private val country = "us"
    private val apiKey = Secrets.NEWS_API_KEY
    private val sourceEndpoint = "https://newsapi.org/v2/top-headlines/sources?"

    suspend fun fetchSources(): List<SourceRaw>{
        val response: SourceResponse =
            httpClient.get("${sourceEndpoint}&country=$country&category=$category&apiKey=$apiKey").body()

        return if (response.status == "ok") response.sourceList
        else emptyList()
    }
}
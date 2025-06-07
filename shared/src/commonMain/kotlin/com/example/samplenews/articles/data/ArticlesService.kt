package com.example.samplenews.articles.data

import com.example.samplenews.Secrets
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService (private val httpClient: HttpClient) {

    private val language = "en"
    private val apiKey = Secrets.NEWS_API_KEY
    private val apiEndpoint = "https://newsapi.org/v2/top-headlines?"

    suspend fun fetchArticles(category: String): List<ArticleRaw>{
        val response: ArticlesResponse =
            httpClient.get("${apiEndpoint}language=$language&category=$category&apiKey=$apiKey").body()
        // we are just interested in the body and not other headers in the request as of now

        return if (response.status == "ok")
            response.articleList
        else
            emptyList()
    }
}


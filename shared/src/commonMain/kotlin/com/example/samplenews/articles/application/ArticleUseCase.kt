package com.example.samplenews.articles.application

import com.example.samplenews.articles.data.ArticleRaw
import com.example.samplenews.articles.data.ArticlesService
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class ArticleUseCase(private val articlesService: ArticlesService) {
    suspend fun getArticles() : List<Article>{
        val articleListRaw =
            articlesService.fetchArticles("technology") + articlesService.fetchArticles("business")

        return mappedArticles(articleListRaw).stableShuffle(42L)
    }

    private fun <T> List<T>.stableShuffle(seed: Long): List<T> = this.shuffled(Random(seed))


    private fun mappedArticles(articleListRaw: List<ArticleRaw>): List<Article> =
        articleListRaw.map { articleRaw ->
            Article(
                title = articleRaw.title.split(" - ")[0],
                description = articleRaw.description ?: "Click to read news content",
                date = presentDate(articleRaw.date),
                imageUrl = articleRaw.imageUrl ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080",
                publisher = articleRaw.source.name
            )
        }

    private fun presentDate(date: String): String {
        val now = Clock.System.now()
        val parsedInstant = Instant.parse(date)
        val nowLocal = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val parsedLocal = parsedInstant.toLocalDateTime(TimeZone.currentSystemDefault())

        val duration = now - parsedInstant
        val minutes = duration.inWholeMinutes
        val hours = duration.inWholeHours
        val days = duration.inWholeDays

        return when {
            minutes < 1 -> "Just now"
            minutes < 60 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
            hours < 24 -> "$hours hour${if (hours > 1) "s" else ""} ago"
            days < 7 -> "$days day${if (days > 1) "s" else ""} ago"
            nowLocal.year == parsedLocal.year -> {
                // Same year, show "23 May"
                val day = parsedLocal.date.dayOfMonth
                val month = parsedLocal.date.month.name.lowercase().replaceFirstChar { it.uppercase() }
                "$day $month"
            }
            else -> {
                // Previous year, show "23 May, 2024"
                val day = parsedLocal.date.dayOfMonth
                val month = parsedLocal.date.month.name.lowercase().replaceFirstChar { it.uppercase() }
                "$day $month, ${parsedLocal.date.year}"
            }
        }
    }


}
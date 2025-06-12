package com.example.samplenews.articles.application

import com.example.samplenews.articles.data.ArticleRaw
import com.example.samplenews.articles.data.ArticlesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class ArticleUseCase(private val repository: ArticlesRepository) {
    suspend fun getArticles(forceFetch: Boolean) : List<Article>{
        val articleListRaw =
            repository.fetchArticles(forceFetch)

        return mappedArticles(articleListRaw).stableShuffle(42L)
    }

    // we create a list extension function that will mix both business and tech articles
    private fun <T> List<T>.stableShuffle(seed: Long): List<T> = this.shuffled(random = Random(seed))


    private fun mappedArticles(articleListRaw: List<ArticleRaw>): List<Article> =
        articleListRaw.map { articleRaw ->
            Article(
                title = articleRaw.title.split(" - ")[0],
                description = articleRaw.description ?: "Click to read the official publication",
                date = presentDate(articleRaw.date),
                imageUrl = articleRaw.imageUrl ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080",
                publisher = articleRaw.source.name,
                author = articleRaw.author,
                urlToPage = articleRaw.urlToPage
            )
        }

    private fun presentDate(date: String): String {

        val nowInstant = Clock.System.now()
        val parsedInstant = Instant.parse(date)
        val currTz = TimeZone.currentSystemDefault()
        val nowLocalTz = nowInstant.toLocalDateTime(currTz)
        val parsedLocalTz = parsedInstant.toLocalDateTime(currTz)

        val duration = nowInstant - parsedInstant
        val minutes = duration.inWholeMinutes
        val hours = duration.inWholeHours
        val days = duration.inWholeDays
        val weeks = days/7
        val day = parsedLocalTz.date.dayOfMonth
        val month = parsedLocalTz.date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val year = parsedLocalTz.date.year

        return when {
            minutes < 1 -> "Just now"
            minutes < 60 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
            hours < 24 -> "$hours hour${if (hours > 1) "s" else ""} ago"
            days < 7 -> "$days day${if (days > 1) "s" else ""} ago"
            weeks < 4 -> "$weeks week${if (weeks > 1) "s" else ""} ago"
            nowLocalTz.year == parsedLocalTz.year -> {
                // Same year, show "DD Month"
                "$month $day"
            }
            else -> {
                // Previous year, show "DD Month, YYYY"
                "$day $month, $year"
            }
        }
    }


}
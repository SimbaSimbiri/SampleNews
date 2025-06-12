package com.example.samplenews.articles.data

import com.example.samplenews.db.SampleNewsDatabase

class ArticlesDataSource(private val db: SampleNewsDatabase) {

    fun getAllArticles(): List<ArticleRaw> =
        db.articleQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        // inserting articles received from the service has to be a transaction so that if one fails,
        // all fail
        db.articleQueries.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }

    fun removeAllArticles() = db.articleQueries.removeAllArticles()


    private fun mapToArticleRaw(
        title: String,
        description: String?,
        date: String,
        imageUrl: String?,
        publisher: String,
        author: String?,
        urlToPage: String
    ): ArticleRaw = ArticleRaw(
        title = title,
        description = description,
        date = date,
        imageUrl = imageUrl,
        author = author,
        source = SourceArt(name = publisher),
        urlToPage = urlToPage
    )


    private fun insertArticle(article: ArticleRaw) {
        db.articleQueries.insertArticle(
            title = article.title,
            description = article.description,
            date = article.date,
            imageUrl = article.imageUrl,
            publisher = article.source.name,
            author = article.author,
            urlToPage = article.urlToPage
        )
    }


}
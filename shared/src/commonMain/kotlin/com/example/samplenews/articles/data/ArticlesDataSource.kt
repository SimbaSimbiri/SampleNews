package com.example.samplenews.articles.data

import com.example.samplenews.db.SampleNewsDatabase

class ArticlesDataSource(private val db: SampleNewsDatabase) {

    fun getAllArticles(): List<ArticleRaw> =
        db.articleQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
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
        content: String?,
        date: String,
        imageUrl: String?,
        publisher: String
    ): ArticleRaw = ArticleRaw(
        title = title,
        description = description,
        content = content,
        date = date,
        imageUrl = imageUrl,
        source = SourceArt(name = publisher)
    )


    private fun insertArticle(article: ArticleRaw) {
        db.articleQueries.insertArticle(
            title = article.title,
            description = article.description,
            content = article.content,
            date = article.date,
            imageUrl = article.imageUrl,
            publisher = article.source.name
        )
    }




}
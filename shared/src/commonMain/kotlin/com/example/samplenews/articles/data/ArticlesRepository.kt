package com.example.samplenews.articles.data

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if (forceFetch) {
            // we first have to clear the database to prevent infinite articles/ duplicate articles
            dataSource.removeAllArticles()
            return articleRawsBackEnd()
        }

        // we first fetch data from the db and check if its not empty
        val articlesDb = dataSource.getAllArticles()

        return articlesDb.ifEmpty {
            articleRawsBackEnd()
        }

    }

    private suspend fun articleRawsBackEnd(): List<ArticleRaw> {
        val fetchedBackEnd =
            service.fetchArticles("technology") + service.fetchArticles("business")
        dataSource.insertArticles(fetchedBackEnd)

        return fetchedBackEnd
    }
}
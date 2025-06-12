package com.example.samplenews.articles.data

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun fetchArticles(forceFetch: Boolean): List<ArticleRaw> {
        // once the UI sends us intent of the user wanting a refreshed list of articles, we
        // initiate a new fetch from the backend
        if (forceFetch) {
            // we first have to clear the database to prevent infinite articles/ duplicate articles
            // being inserted in our database
            dataSource.removeAllArticles()
            return articlesRawFromService()
        }

        // we first fetch data from the db and check if its not empty
        val articlesRawFromDb = dataSource.getAllArticles()

        return articlesRawFromDb.ifEmpty { // if db is empty, we fetch from the backend
            articlesRawFromService()
        }

    }

    private suspend fun articlesRawFromService(): List<ArticleRaw> {
        val fetchedBackEnd =
            service.fetchArticles("technology") + service.fetchArticles("business")
        dataSource.insertArticles(fetchedBackEnd)

        return fetchedBackEnd
    }
}
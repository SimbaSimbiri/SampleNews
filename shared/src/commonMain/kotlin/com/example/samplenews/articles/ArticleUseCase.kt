package com.example.samplenews.articles

class ArticleUseCase(private val articlesService: ArticlesService) {
    suspend fun getArticles() : List<Article>{
        val articlesRaw = articlesService.fetchArticles()

        return mappedArticles(articlesRaw)
    }

    private fun mappedArticles(articlesRaw: List<ArticleRaw>): List<Article> =
        articlesRaw.map { articleRaw ->
            Article(
                title = articleRaw.title,
                description = articleRaw.description ?: "Click to find out more",
                imageUrl = articleRaw.imageUrl ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080",
                date = articleRaw.date
            )
        }

}
package com.example.samplenews.sources.data

import com.example.samplenews.db.SampleNewsDatabase

class SourcesDataSource(private val db: SampleNewsDatabase) {
    fun getAllSources(): List<SourceRaw> =
        db.sourceQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourceRaw>) {
        db.sourceQueries.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    fun removeAllSources() = db.sourceQueries.removeAllSources()

    private fun insertSource(source: SourceRaw) {
        db.sourceQueries.insertSource(
            name = source.name,
            description = source.description,
            homepage = source.homepage,
            country = source.country,
            language = source.language
        )
    }


    private fun mapToSourceRaw(
        name: String, description: String?, homepage: String, country: String, language: String
    ): SourceRaw = SourceRaw(
        name = name,
        description = description ?: "Click to go to source homepage",
        homepage = homepage,
        country = country,
        language = language
    )

}
package com.example.samplenews.sources.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {

    suspend fun fetchSources(forceFetch: Boolean): List<SourceRaw> {
        if (forceFetch){
            dataSource.removeAllSources()
            return sourcesRawFromService()
        }
        val sourcesRawFromDb = dataSource.getAllSources()

        return sourcesRawFromDb.ifEmpty {
            sourcesRawFromService()
        }
    }

    private suspend fun sourcesRawFromService(): List<SourceRaw> {
        val fetchedBackEnd =
            service.fetchSources("technology") + service.fetchSources("business")
        dataSource.insertSources(fetchedBackEnd)

        return fetchedBackEnd
    }
}
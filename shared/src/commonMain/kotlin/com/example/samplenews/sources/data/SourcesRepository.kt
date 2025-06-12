package com.example.samplenews.sources.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {

    suspend fun getSources(forceFetch: Boolean): List<SourceRaw> {
        if (forceFetch){
            dataSource.removeAllSources()
            return sourcesRawFromService()
        }
        val sourcesFromDb = dataSource.getAllSources()

        return sourcesFromDb.ifEmpty {
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
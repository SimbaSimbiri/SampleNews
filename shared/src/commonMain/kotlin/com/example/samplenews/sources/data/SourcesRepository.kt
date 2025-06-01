package com.example.samplenews.sources.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {

    suspend fun getSources(forceFetch: Boolean): List<SourceRaw> {
        if (forceFetch){
            dataSource.removeAllSources()
            return sourceRawsBackEnd()
        }
        val sourcesDb = dataSource.getAllSources()

        return if (sourcesDb.isEmpty()) {
            sourceRawsBackEnd()
        } else {
            sourcesDb
        }
    }

    private suspend fun sourceRawsBackEnd(): List<SourceRaw> {
        val fetchedBackEnd =
            service.fetchSources("technology") + service.fetchSources("business")
        dataSource.insertSources(fetchedBackEnd)

        return fetchedBackEnd
    }
}
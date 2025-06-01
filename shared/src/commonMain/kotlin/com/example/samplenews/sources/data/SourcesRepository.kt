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
        println("Got ${sourcesDb.size} sources from db")

        if (sourcesDb.isEmpty()) {
            return sourceRawsBackEnd()
        } else {
            return sourcesDb
        }
    }

    private suspend fun sourceRawsBackEnd(): List<SourceRaw> {
        val fetchedBackEnd =
            service.fetchSources("technology") + service.fetchSources("business")
        dataSource.insertSources(fetchedBackEnd)
        println("Got ${fetchedBackEnd.size} sources from backend")

        return fetchedBackEnd
    }
}
package com.example.samplenews.sources.application

import com.example.samplenews.sources.data.SourceRaw
import com.example.samplenews.sources.data.SourceService

class SourceUseCase(private val sourceService: SourceService) {
    suspend fun getSources(): List<Source> {
        val sourceListRaw : List<SourceRaw> =  sourceService.fetchSources()

        return mappedRawDomain(sourceListRaw)
    }

    private fun mappedRawDomain(sourceListRaw: List<SourceRaw>): List<Source> =
        sourceListRaw.map { sourceRaw ->
            Source(
                name = sourceRaw.name,
                description = sourceRaw.description,
                homepage = sourceRaw.homepage,
                origin = "${sourceRaw.language} - ${sourceRaw.country}"
            )
        }
}
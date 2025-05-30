package com.example.samplenews.sources.application

import com.example.samplenews.sources.data.SourceRaw
import com.example.samplenews.sources.data.SourceService
import kotlin.random.Random

class SourceUseCase(private val sourceService: SourceService) {
    suspend fun getSources(): List<Source> {
        val sourceListRaw : List<SourceRaw> =  sourceService.fetchSources("technology") +
                sourceService.fetchSources("business")

        return mappedRawDomain(sourceListRaw).stableShuffle(42L)
    }

    private fun <T> List<T>.stableShuffle(seed: Long): List<T> = this.shuffled(Random(seed))


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
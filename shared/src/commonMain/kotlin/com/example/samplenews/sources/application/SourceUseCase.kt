package com.example.samplenews.sources.application

import com.example.samplenews.sources.data.SourceRaw
import com.example.samplenews.sources.data.SourcesRepository
import kotlin.random.Random

class SourceUseCase(private val repository: SourcesRepository) {
    suspend fun getSources(forceFetch: Boolean): List<Source> {
        val sourceListRaw : List<SourceRaw> = repository.fetchSources(forceFetch)

        return mappedSources(sourceListRaw).stableShuffle(19L)
    }

    private fun <T> List<T>.stableShuffle(seed: Long): List<T> = this.shuffled(Random(seed))


    private fun mappedSources(sourceListRaw: List<SourceRaw>): List<Source> =
        sourceListRaw.map { sourceRaw ->
            Source(
                name = sourceRaw.name,
                description = sourceRaw.description,
                homepage = sourceRaw.homepage,
                origin = "${sourceRaw.language} - ${sourceRaw.country}"
            )
        }
}
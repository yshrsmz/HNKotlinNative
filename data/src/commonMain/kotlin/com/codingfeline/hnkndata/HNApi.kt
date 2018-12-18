package com.codingfeline.hnkndata

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list

internal expect val ApplicationDispatcher: CoroutineDispatcher

class HNApi {
    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(Story::class, Story.serializer())
            }
        }
    }

    suspend fun fetchTopStoryIds(): List<Int> {
        val result: String = client.get {
            url("https://hacker-news.firebaseio.com/v0/topstories.json")
        }
        return JSON.parse(IntSerializer.list, result)
    }

    private suspend fun fetchStory(id: Int): Story {
        return client.get<Story> {
            url("https://hacker-news.firebaseio.com/v0/item/$id.json")
        }
    }

    suspend fun fetchStories(ids: List<Int>): List<Story> {
        val deferredList = ids.map { id ->
            coroutineScope {
                async { fetchStory(id) }
            }
        }

        return deferredList.awaitAll()
    }
}

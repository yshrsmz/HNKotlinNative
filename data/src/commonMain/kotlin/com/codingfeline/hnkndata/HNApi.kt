package com.codingfeline.hnkndata

import io.ktor.client.HttpClient
import io.ktor.client.call.ReceivePipelineException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.*
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
        val result = client.get<String> {
            url("https://hacker-news.firebaseio.com/v0/topstories.json")
        }
        return JSON.parse(IntSerializer.list, result)
    }

    fun fetchTopStoryIds(callback: (ids: List<Int>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = client.get {
                    url("https://hacker-news.firebaseio.com/v0/topstories.json")
                }
                val ids = JSON.parse(IntSerializer.list, result)
                callback(ids)
            }
        }
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

    @KtorExperimentalAPI
    fun fetchStories(ids: List<Int>, callback: (stories: List<Story>) -> Unit) {
        GlobalScope.apply {
            val deferred = ids.map { id -> async(ApplicationDispatcher) { fetchStory(id) } }

            launch(ApplicationDispatcher) {
                try {
                    val stories = deferred.map { it.await() }

                    println(stories)
                    callback(stories)
                } catch (e: ReceivePipelineException) {
                    println("error: $e, ${e.message}, ${e.cause}, ${e.info}")
                }
            }
        }
    }
}

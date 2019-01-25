package com.codingfeline.hnkndata

import com.codingfeline.github.EnumTestDocument
import com.codingfeline.github.Type
import io.ktor.client.HttpClient
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
        //        install(JsonFeature) {
        //            serializer = KotlinxSerializer().apply {
//                setMapper(Story::class, Story.serializer())
//            }
//    }
    }

    init {
        val test = EnumTestDocument.UserQuery.requestBody(EnumTestDocument.UserQuery.Variables("yshrsmz", Type.BAR))
        println("EnumTest: $test")
    }

    suspend fun fetchTopStoryIds(): List<Int> {
        val result: String = client.get {
            url("https://hacker-news.firebaseio.com/v0/topstories.json")
        }
        return JSON.parse(IntSerializer.list, result)
    }

    private suspend fun fetchStory(id: Int): Story {
        return client.get<String> {
            url("https://hacker-news.firebaseio.com/v0/item/$id.json")
        }.let { JSON.parse(Story.serializer(), it) }
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

package com.codingfeline.hnkndata

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal expect val ApplicationDispatcher: CoroutineDispatcher

class HNApi {
    private val client = HttpClient()

    fun fetchTopStories(callback: (ids: String) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = client.get {
                    url("https://hacker-news.firebaseio.com/v0/topstories.json")
                }
                callback(result)
            }
            Unit
        }
    }
}

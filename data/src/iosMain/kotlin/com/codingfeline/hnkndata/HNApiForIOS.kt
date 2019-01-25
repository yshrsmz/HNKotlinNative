package com.codingfeline.hnkndata

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HNApiForIOS(
    private val hnApi: HNApi
) : CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = ApplicationDispatcher + job

    fun fetchTopStoryIds(callback: (ids: List<Int>) -> Unit) {
        launch {
            println("HNApiForIOS: fetchTopStoryIds")
            val ids = hnApi.fetchTopStoryIds()
            callback(ids)
        }
    }

    fun fetchStories(ids: List<Int>, callback: (stories: List<Story>) -> Unit) {
        launch {
            println("HNApiForIOS: fetchTopStories")
            val stories = hnApi.fetchStories(ids)
            callback(stories)
        }
    }

    fun dispose() {
        job.cancel()
    }
}

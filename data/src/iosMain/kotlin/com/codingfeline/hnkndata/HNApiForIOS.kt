package com.codingfeline.hnkndata

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HNApiForIOS(
    private val hnApi: HNApi
) : CoroutineScope {

    override val coroutineContext: CoroutineContext = ApplicationDispatcher + SupervisorJob()

    fun fetchTopStoryIds(callback: (ids: List<Int>) -> Unit) {
        launch {
            val ids = hnApi.fetchTopStoryIds()
            callback(ids)
        }
    }

    fun fetchStories(ids: List<Int>, callback: (stories: List<Story>) -> Unit) {
        launch {
            val stories = hnApi.fetchStories(ids)
            callback(stories)
        }
    }

}

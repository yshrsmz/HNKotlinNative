package com.codingfeline.github

import com.codingfeline.hnkndata.ApplicationDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GitHubApiForIOS(
    private val api: GitHubApi
) : CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = ApplicationDispatcher + job

    fun fetchUser(callback: (user: User?) -> Unit) {
        launch {
            val user = api.fetchUser()
            callback(user)
        }
    }

    fun dispose() {
        job.cancel()
    }
}

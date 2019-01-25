package com.codingfeline.github

import android.os.Build
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Ignore
import kotlin.test.Test

@Config(
    sdk = [Build.VERSION_CODES.P],
    manifest = Config.NONE
)
@RunWith(RobolectricTestRunner::class)
class GitHubApiTest {

    val api = GitHubApi()

    @Ignore
    @Test
    fun test() {
        runBlocking {
            val viewer = api.fetchUser()

            println(viewer)
        }
    }
}

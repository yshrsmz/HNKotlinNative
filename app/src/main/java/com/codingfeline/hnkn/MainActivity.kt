package com.codingfeline.hnkn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codingfeline.hnkndata.HNApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    val api by lazy { HNApi() }

    val job = SupervisorJob()

    val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uiScope.launch {
            val ids = api.fetchTopStoryIds()
            val stories = api.fetchStories(ids.subList(0, 10))

            Log.d(TAG, "$stories")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}

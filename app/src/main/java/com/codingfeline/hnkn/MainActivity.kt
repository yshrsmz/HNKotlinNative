package com.codingfeline.hnkn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codingfeline.hnkndata.HNApi

class MainActivity : AppCompatActivity() {

    val api by lazy { HNApi() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api.fetchTopStoryIds { ids ->
            Log.d("MainActivity", "ids: $ids")
            Log.d("MainActivity", "thread: ${Thread.currentThread().name}")

            api.fetchStories(ids.subList(0, 10)) { stories ->
                println(stories)
            }
        }
    }
}

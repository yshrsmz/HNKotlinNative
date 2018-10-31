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

        api.fetchTopStories {
            Log.d("MainActivity", "ids: $it")
            Log.d("MainActivity", "thread: ${Thread.currentThread().name}")
        }
    }
}

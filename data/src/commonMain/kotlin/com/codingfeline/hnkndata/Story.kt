package com.codingfeline.hnkndata

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val id: Int,
    @Optional val deleted: Boolean? = null,
    val type: String,
    val by: String,
    val time: Long,
    @Optional val text: String = "",
    @Optional val dead: Boolean? = null,
    @Optional val parent: Int? = null,
    @Optional val poll: Int? = null,
    @Optional val kids: List<Int> = emptyList(),
    val url: String,
    val score: Int,
    val title: String,
    @Optional val parts: List<Int>? = null,
    @Optional val descendants: Int? = null
)

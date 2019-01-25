package com.codingfeline.github

import com.codingfeline.kgql.core.KgqlError
import com.codingfeline.kgql.core.KgqlResponse
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


// "user": {
//      "id": "MDQ6VXNlcjY1NDg4OQ==",
//      "login": "yshrsmz",
//      "bio": "Android/Web developer",
//      "avatarUrl": "https://avatars2.githubusercontent.com/u/654889?v=4",
//      "company": "CyberAgent, Inc.",
//      "createdAt": "2011-03-07T04:05:18Z"
//    }

@Serializable
data class UserWrapper(
    val user: User
)

@Serializable
data class User(
    val id: String,
    val login: String,
    val bio: String,
    val avatarUrl: String,
    val company: String,
    val createdAt: String
)

@Serializable
data class ViewerResponse(
    @Optional override val data: UserWrapper? = null,
    @Optional override val errors: List<KgqlError>? = null
) : KgqlResponse<UserWrapper>

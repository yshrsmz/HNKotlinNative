package com.codingfeline.github

import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.Url
import kotlinx.serialization.json.JSON

const val TOKEN = ""

class GitHubApi {

    private val client = HttpClient {
        //        install(JsonFeature)
    }

    suspend fun fetchUser(): User? {
        val json = UserDocument.UserQuery.requestBody(UserDocument.UserQuery.Variables("yshrsmz"))

        println("request json: $json")
        val response = client.post<String>(url = Url("https://api.github.com/graphql")) {
            body = json

            headers {
                append("Authorization", "bearer $TOKEN")
            }
        }

        println("response json: $response")

        val res = JSON.parse(ViewerResponse.serializer(), response)

        println("response: $res")

        return res.data?.user
    }
}

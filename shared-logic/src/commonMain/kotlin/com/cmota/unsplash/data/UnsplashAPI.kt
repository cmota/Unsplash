package com.cmota.unsplash.data

import com.cmota.unsplash.data.model.Image
import com.cmota.unsplash.data.model.ImageData
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val ACCESS_KEY = "ngak5Lv2ZsDvYfnAJjyMP0mnV23pWs5hcvOBXceV3Wc"

private const val BASE_URL = "https://api.unsplash.com"
private const val PHOTOS = "$BASE_URL/photos"
private const val SEARCH = "$BASE_URL/search/photos"

private const val PARAMETER_QUERY = "query"
private const val AUTHORIZATION_CLIENT_ID = "Client-ID"

public object UnsplashAPI {

    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client: HttpClient = HttpClient {

        defaultRequest {
            header(HttpHeaders.Authorization, "$AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
        }

        install(ContentNegotiation) {
            json(nonStrictJson)
        }

        install(Logging) {
            logger = HttpClientLogger
            level = LogLevel.ALL
        }
    }

    public suspend fun fetchImages(): List<Image> = client.get(PHOTOS) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }.body()

    public suspend fun searchImages(search: String): ImageData = client.get(SEARCH) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        parameter(PARAMETER_QUERY, search)
    }.body()
}
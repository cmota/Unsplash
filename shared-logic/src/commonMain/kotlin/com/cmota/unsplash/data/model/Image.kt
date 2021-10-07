package com.cmota.unsplash.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val description: String?,
    val user: User,
    val urls: ImageUrl
)

@Serializable
data class ImageUrl(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
)

@Serializable
data class User(
    val id: String,
    val username: String,
    val name: String?
)
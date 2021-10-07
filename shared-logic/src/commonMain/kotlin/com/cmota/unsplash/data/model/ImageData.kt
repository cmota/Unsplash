package com.cmota.unsplash.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageData(
    val total: Int,
    val total_pages: Int,
    val results: List<Image>
)
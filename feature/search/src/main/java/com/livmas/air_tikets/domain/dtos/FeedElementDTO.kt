package com.livmas.air_tikets.domain.dtos

data class FeedElementDTO(
    val id: Long,
    val title: String,
    val town: String,
    val price: Int
)

package com.livmas.search.models

data class GetFeedResponseBody(
    val offers: List<FeedElementModel>
) {
    data class FeedElementModel(
        val id: Long,
        val title: String,
        val town: String,
        val price: PriceModel
    ) {
    }
}


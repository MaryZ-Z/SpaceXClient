package com.example.spacexclient.data.launches

data class PagingResponse(
    val docs: List<QueryLaunches>,
    val limit: Int,
    val page: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean,
    val prevPage: Int?,
    val nextPage: Int?
)
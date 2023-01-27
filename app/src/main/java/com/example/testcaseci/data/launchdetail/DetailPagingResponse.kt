package com.example.testcaseci.data.launchdetail

data class DetailPagingResponse(
    val docs: List<Crew>,
    val limit: Int,
    val page: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean,
    val prevPage: Int?,
    val nextPage: Int?
)

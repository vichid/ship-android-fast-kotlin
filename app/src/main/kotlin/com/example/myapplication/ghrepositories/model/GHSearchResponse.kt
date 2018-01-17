package com.example.myapplication.ghrepositories.model

import com.google.gson.annotations.SerializedName

data class GHSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("items")
    val items: List<GHRepository> = emptyList(),
    val nextPage: Int? = null
)
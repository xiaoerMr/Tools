package com.sai.tools.http

data class ResTask(
    val count: Int,
    val list: List<Item>,
    val order: Int,
    val page: Int,
    val size: Int,
    val totalPage: Int
)

data class Item(
    val acceptUnit: String,
    val receiver: String,
    val sendingUnit: String
)
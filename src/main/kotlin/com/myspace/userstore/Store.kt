package com.myspace.userstore

data class Store(
    var itemName: String? = null,
    var itemType: String? = null,
    var price: Float? = null,
    var quantity: Int? = null,
    var saleNotAllowed: Boolean? = false
)

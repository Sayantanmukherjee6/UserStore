package com.myspace.userstore

data class User(
    var age: Int? = null,
    var canPurchase: Boolean = false,
    var status: Boolean = false,
    var userName: String? = null
)

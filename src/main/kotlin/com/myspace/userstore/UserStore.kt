package com.myspace.userstore

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserStore(
    val user: User? = null,
    val store: Store? = null
)

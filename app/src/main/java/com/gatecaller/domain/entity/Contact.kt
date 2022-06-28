package com.gatecaller.domain.entity

import com.gatecaller.data.entity.ApiContactDatabase

data class Contact(
    val id: Int,
    val number: String,
    val name: String
)

fun Contact.toApiContactDatabase() = ApiContactDatabase(
    id = 0,
    name = this.name,
    number = this.number
)
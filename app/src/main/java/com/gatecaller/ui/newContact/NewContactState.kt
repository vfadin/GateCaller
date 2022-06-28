package com.gatecaller.ui.newContact

data class NewContactState(
    val name: String = "",
    val nameError : String? = null,
    val number: String = "",
    val numberError : String? = null
)
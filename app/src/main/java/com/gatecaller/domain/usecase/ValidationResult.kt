package com.gatecaller.domain.usecase

data class ValidationResult(
    val successful : Boolean,
    val errorMessage : String? = null
)

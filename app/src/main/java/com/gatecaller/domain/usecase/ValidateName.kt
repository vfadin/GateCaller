package com.gatecaller.domain.usecase

class ValidateName {

    fun execute(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Имя не может быть пустым"
            )
        }
        return ValidationResult(successful = true)
    }
}
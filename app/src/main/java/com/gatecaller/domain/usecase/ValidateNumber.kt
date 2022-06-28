package com.gatecaller.domain.usecase

import android.telephony.PhoneNumberUtils

class ValidateNumber {

    fun execute(number: String): ValidationResult {
        if (number.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Номер не может быть пустым"
            )
        }
        if (!PhoneNumberUtils.isGlobalPhoneNumber(number)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Номер должен быть существующим"
            )
        }
        return ValidationResult(successful = true)
    }
}
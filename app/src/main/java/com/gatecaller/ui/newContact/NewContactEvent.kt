package com.gatecaller.ui.newContact

sealed class NewContactEvent {
    data class NameChanged(val name: String) : NewContactEvent()
    data class NumberChanged(val number: String) : NewContactEvent()
    object Submit : NewContactEvent()
}
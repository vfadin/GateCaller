package com.gatecaller.ui.existContact

import com.gatecaller.domain.entity.Contact

sealed class ExistContactEvent {
    data class OnItemClick(val contact: Contact) : ExistContactEvent()
}
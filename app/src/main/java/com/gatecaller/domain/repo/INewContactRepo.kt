package com.gatecaller.domain.repo

import com.gatecaller.domain.entity.Contact

interface INewContactRepo {
    suspend fun addToDatabase(contact: Contact)
}
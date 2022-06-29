package com.gatecaller.data.repo

import com.gatecaller.data.database.ContactDatabase
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.entity.toApiContactDatabase
import com.gatecaller.domain.repo.INewContactRepo
import javax.inject.Inject

class NewContactRepo @Inject constructor(
    private val database: ContactDatabase
) : INewContactRepo {
    override suspend fun addToDatabase(contact: Contact) {
        database.contactDao().insert(contact.toApiContactDatabase())
    }
}
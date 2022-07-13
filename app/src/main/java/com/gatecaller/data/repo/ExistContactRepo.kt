package com.gatecaller.data.repo

import android.content.Context
import com.gatecaller.data.database.ContactDatabase
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.entity.toApiContactDatabase
import com.gatecaller.domain.repo.IExistContactRepo
import com.gatecaller.data.ContactHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExistContactRepo @Inject constructor(
    private val database: ContactDatabase,
    @ApplicationContext private val context: Context
) : IExistContactRepo {
    override suspend fun addToDatabase(contact: Contact) {
        database.contactDao().insert(contact.toApiContactDatabase())
    }

    override suspend fun getContacts(): List<Contact> = ContactHandler.getPhoneContacts(context)
}
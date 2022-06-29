package com.gatecaller.data.repo

import com.gatecaller.data.database.ContactDatabase
import com.gatecaller.data.entity.toContact
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.IHomeRepo

class HomeRepo(
    private val database: ContactDatabase
) : IHomeRepo {
    override suspend fun getFromDatabase(): List<Contact> {
        return database.contactDao().getAll().map {
            it.toContact()
        }
    }

    override suspend fun deleteFromDatabaseById(id: Int) {
        database.contactDao().deleteById(id)
    }
}
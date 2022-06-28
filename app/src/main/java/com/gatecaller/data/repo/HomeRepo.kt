package com.gatecaller.data.repo

import com.gatecaller.data.database.ContactDatabase
import com.gatecaller.data.entity.toContact
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.IHomeRepo

class HomeRepo(
    private val database: ContactDatabase
) : IHomeRepo {
    override suspend fun getFromDatabase(): List<Contact> {
        return database.newsDao().getAll().map {
            it.toContact()
        }
    }
}
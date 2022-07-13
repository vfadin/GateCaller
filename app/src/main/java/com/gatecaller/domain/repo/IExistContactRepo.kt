package com.gatecaller.domain.repo

import com.gatecaller.domain.entity.Contact

interface IExistContactRepo {
    suspend fun addToDatabase(contact: Contact)
    suspend fun getContacts() : List<Contact>
}
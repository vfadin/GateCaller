package com.gatecaller.domain.repo

import com.gatecaller.domain.entity.Contact

interface IHomeRepo {
    fun getFromDatabase(): List<Contact>
    fun addToDatabase(contact: Contact)
}
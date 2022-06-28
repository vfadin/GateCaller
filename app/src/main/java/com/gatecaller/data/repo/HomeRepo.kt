package com.gatecaller.data.repo

import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.IHomeRepo

class HomeRepo : IHomeRepo {
    override fun getFromDatabase(): List<Contact> {
        TODO("Not yet implemented")
    }

    override fun addToDatabase(contact: Contact) {
        TODO("Not yet implemented")
    }

}
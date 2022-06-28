package com.gatecaller.domain.repo

import com.gatecaller.domain.entity.Contact

interface IHomeRepo {
    suspend fun getFromDatabase(): List<Contact>
}
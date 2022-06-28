package com.gatecaller.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gatecaller.domain.entity.Contact

@Entity(tableName = "contacts")
data class ApiContactDatabase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val number: String?,
)

fun ApiContactDatabase.toContact() = Contact(
    id = this.id,
    name = this.name ?: "",
    number = this.number ?: ""
)
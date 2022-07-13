package com.gatecaller.ui.home

import android.content.Context
import android.provider.ContactsContract
import com.gatecaller.domain.entity.Contact


abstract class ContactHandler() {
    companion object {
        fun getPhoneContacts(context: Context): List<Contact> {
            val contactsList = mutableListOf<Contact>()
            val phoneCursor = context.contentResolver?.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                null,
                null
            )
            phoneCursor?.apply {
                while (moveToNext()) {
                    contactsList.add(
                        Contact(
                            getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                                .toInt(),
                            getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                            getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        )
                    )
                }
                close()
            }
            return contactsList
        }
    }
}
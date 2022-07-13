package com.gatecaller.ui.existContact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.IExistContactRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExistContactViewModel @Inject constructor(
    private val existContactRepo: IExistContactRepo
) : ViewModel() {
    private val _contactState = MutableLiveData<List<Contact>?>(null)
    val contactState: LiveData<List<Contact>?> = _contactState

    init {
        viewModelScope.launch {
            _contactState.value = existContactRepo.getContacts()
        }
    }

    fun addToDatabase(contact: Contact) {
        viewModelScope.launch {
            existContactRepo.addToDatabase(contact)
        }
    }
}
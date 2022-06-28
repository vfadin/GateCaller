package com.gatecaller.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.IHomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: IHomeRepo
) : ViewModel() {

    private val _contactState: MutableLiveData<List<Contact>?> = MutableLiveData(null)
    val contactState: LiveData<List<Contact>?> = _contactState

    init {
        viewModelScope.launch {
            println(homeRepo.getFromDatabase())
            _contactState.value = homeRepo.getFromDatabase()
        }
    }

}
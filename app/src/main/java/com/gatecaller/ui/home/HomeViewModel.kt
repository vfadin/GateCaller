package com.gatecaller.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.IHomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: IHomeRepo
) : ViewModel() {

    private val _contactState: MutableLiveData<List<Contact>?> = MutableLiveData(null)
    val contactState: LiveData<List<Contact>?> = _contactState

    val dataList = mutableListOf(
        Contact(1, "+79995497799", "Gate1"),
        Contact(2, "+7913957799", "Gate2"),
    )

    init {
        _contactState.value = dataList
    }

    fun checkAdd() {
        dataList.add(Contact(3, "+7913951399", "Gate3"))
        _contactState.value = null
        _contactState.value = _contactState.value
    }
}
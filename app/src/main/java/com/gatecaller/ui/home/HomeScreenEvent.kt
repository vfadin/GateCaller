package com.gatecaller.ui.home

sealed class HomeScreenEvent {
    object OnAddClick : HomeScreenEvent()
    data class OnItemClick(val number : String) : HomeScreenEvent()
    data class OnDeleteClick(val id : Int) : HomeScreenEvent()
}
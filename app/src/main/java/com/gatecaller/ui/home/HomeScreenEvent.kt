package com.gatecaller.ui.home

sealed class HomeScreenEvent {
    object AddButtonClick : HomeScreenEvent()
    data class OnItemClick(val number : String) : HomeScreenEvent()
    data class OnItemLongClick(val id : Int) : HomeScreenEvent()
}
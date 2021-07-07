package com.example.medicomgmester.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Function จะเปิดให้ใช้งานเร็วๆนี้"
    }
    val text: LiveData<String> = _text
}
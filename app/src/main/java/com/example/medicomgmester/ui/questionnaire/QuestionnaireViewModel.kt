package com.example.medicomgmester.ui.questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionnaireViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Function จะเปิดให้ใช้งานเร็วๆนี้"
    }
    val text: LiveData<String> = _text
}
package com.tw.mvvm_example.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getWelcomeMessage(): String{
        return "Hello World!"
    }

}
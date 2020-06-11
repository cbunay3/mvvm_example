package com.tw.mvvm_example.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tw.mvvm_example.R
import com.tw.mvvm_example.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_welcome_message.text = viewModel.getWelcomeMessage()
    }
}
package com.tw.mvvm_example.di

import com.tw.mvvm_example.model.services.RetrofitService
import com.tw.mvvm_example.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {  MainViewModel(get()) }

}

val networkModule = module {
    single { RetrofitService().getRetrofitMoviesService() }
}



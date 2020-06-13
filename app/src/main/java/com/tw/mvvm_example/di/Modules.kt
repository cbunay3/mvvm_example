package com.tw.mvvm_example.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.tw.mvvm_example.database.ApplicationDatabase
import com.tw.mvvm_example.model.services.RetrofitService
import com.tw.mvvm_example.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel() }
}

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            ApplicationDatabase::class.java, "application-database"
        ).build()
    }

    //TODO repositories


    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().packageName,
            Context.MODE_PRIVATE
        )
    }
}

val networkModule = module {
    single { RetrofitService().getRetrofitMovieService() }

}
package com.tw.mvvm_example.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.tw.mvvm_example.database.ApplicationDatabase
import com.tw.mvvm_example.model.mappers.MovieMapper
import com.tw.mvvm_example.model.repositories.MovieRepository
import com.tw.mvvm_example.model.services.RetrofitService
import com.tw.mvvm_example.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel(get()) }

}

val repositoryModule = module {
    single { RetrofitService().getRetrofitMoviesService() }
    factory { MovieRepository(get()) }
}

val networkModule = module {
    single { RetrofitService().getRetrofitMoviesService() }
}

val persistenceModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ApplicationDatabase::class.java, "application-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val mapperModule = module {
    factory { MovieMapper() }
}

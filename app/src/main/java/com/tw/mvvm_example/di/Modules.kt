package com.tw.mvvm_example.di

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
    factory { MovieRepository(get(), get()) }
}

val networkModule = module {
    single { RetrofitService().getRetrofitMoviesService() }
}

val dBModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ApplicationDatabase::class.java, "application-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<ApplicationDatabase>().movieDao() }
}

val mapperModule = module {
    factory { MovieMapper() }
}

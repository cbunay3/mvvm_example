package com.tw.mvvm_example.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.tw.mvvm_example.di.mapperModule
import com.tw.mvvm_example.di.networkModule
import com.tw.mvvm_example.di.repositoryModule
import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.dtos.MoviesWrapper
import com.tw.mvvm_example.model.mappers.MovieMapper
import com.tw.mvvm_example.model.models.MovieEntity
import com.tw.mvvm_example.model.repositories.MovieRepository
import com.tw.mvvm_example.model.services.MovieService
import me.linshen.retrofit2.adapter.ApiResponse
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

@RunWith(JUnit4::class)
class MainViewModelTest : KoinTest {
    private val mockMovieService by inject<MovieService>()
    private val mockMovieRepository by inject<MovieRepository>()
    private val mockMovieMapper by inject<MovieMapper>()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun before() {
        startKoin {
            modules(
                listOf(
                    repositoryModule,
                    networkModule,
                    mapperModule
                )
            )
        }
        declareMock<MovieService>()
        declareMock<MovieRepository>()
        declareMock<MovieMapper>()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `should update movies collection and save in the database if internet connection is available`() {
        val mainViewModel = MainViewModel(mockMovieService, mockMovieRepository, mockMovieMapper)
        val lifeCycleOwner = mockLifecycleOwner()
        val moviesWrapper = MoviesWrapper(moviesList())
        val response = Response.success(moviesWrapper)
        val apiResponse = ApiResponse.create(response)
        val responseMovies = MutableLiveData<ApiResponse<MoviesWrapper>>()
        responseMovies.value = apiResponse
        `when`(mockMovieService.getMovies()).thenReturn(responseMovies)
        val observer = mock<Observer<ApiResponse<MoviesWrapper>>>()
        mockMovieService.getMovies().observeForever(observer)

        mainViewModel.getMovies(lifeCycleOwner, true)

        verify(observer).onChanged(apiResponse)
        verify(mockMovieRepository, times(1)).save(moviesWrapper.results)
    }

    @Test
    fun `should retrieve from the database if internet connection is not available`() {
        val mainViewModel = MainViewModel(mockMovieService, mockMovieRepository, mockMovieMapper)
        val lifeCycleOwner = mockLifecycleOwner()
        val responseMovies = MutableLiveData<List<MovieEntity>>()
        val moviesList = databaseMoviesList()
        responseMovies.value = moviesList
        `when`(mockMovieRepository.getAll()).thenReturn(responseMovies)
        val observer = mock<Observer<List<MovieEntity>>>()
        mockMovieRepository.getAll().observeForever(observer)

        mainViewModel.getMovies(lifeCycleOwner, false)

        verify(observer).onChanged(moviesList)
        verify(mockMovieMapper, times(1)).mapToDto(moviesList)
        verifyZeroInteractions(mockMovieService)
    }

    private fun moviesList(): List<MovieDto>{
        return listOf(MovieDto(1, "www.myImage","my overview", "04/10/2020"
            ,"myTitle", "myOriginalTitle","Spanish"));
    }

    private fun databaseMoviesList(): List<MovieEntity>{
        return listOf(MovieEntity(1, "www.myImage","my overview", "04/10/2020"
            ,"myTitle", "myOriginalTitle","Spanish"));
    }


    private fun mockLifecycleOwner(): LifecycleOwner {
        val owner = mock<LifecycleOwner>()
        val lifecycle = LifecycleRegistry(owner)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`<Lifecycle>(owner.lifecycle).thenReturn(lifecycle)
        return owner
    }
}

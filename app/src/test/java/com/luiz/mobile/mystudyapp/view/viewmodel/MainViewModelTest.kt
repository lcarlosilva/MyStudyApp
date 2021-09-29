package com.luiz.mobile.mystudyapp.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luiz.mobile.mystudyapp.domain.repository.MainRepository
import com.luiz.mobile.mystudyapp.view.viewmodel.main.MainViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var infosLiveDataObserver: Observer<String>

    private lateinit var viewModel: MainViewModel

    @Test
    fun `init constructor` () {
        val mainViewModel = MainViewModel(repository)
        Assert.assertNotNull(mainViewModel)
    }

    @Test
    fun `when viewModel request infos return String`() {
        //Arrange
        val text = "Teste"
        val mockMainRepository = MockMainRepository(text)
        viewModel = MainViewModel(mockMainRepository)
        viewModel.infosLiveData.observeForever(infosLiveDataObserver)

        //Act
        viewModel.getInfosDevices()

        //Assert
        verify(infosLiveDataObserver).onChanged(text)
    }

    class MockMainRepository(private val text: String) : MainRepository {
        override fun getDevicesInfos(result: (infosDevices: String) -> Unit) {
            result(text)
        }

    }
}
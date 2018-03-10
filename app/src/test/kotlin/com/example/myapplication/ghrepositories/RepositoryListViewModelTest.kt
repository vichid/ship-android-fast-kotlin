package com.example.myapplication.ghrepositories

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var repositoryListViewModel: RepositoryListViewModel

    lateinit var searchRepositoriesUseCase: SearchRepositoriesUseCase

    @Before
    fun setUp() {
        searchRepositoriesUseCase = mock()
        repositoryListViewModel = RepositoryListViewModel(searchRepositoriesUseCase)
    }

    @Test
    fun shouldNotReturnAnythingOnSearchNextPage() {
        whenever(searchRepositoriesUseCase.execute(any(), any())).thenReturn(Single.never())

        repositoryListViewModel.searchNextPage(0)

        verify(searchRepositoriesUseCase, times(1)).execute(any(), any())
        verifyNoMoreInteractions(searchRepositoriesUseCase)
    }

    @Test
    fun shouldSuccessOnSearchNextPage() {
        val items = GHRepositoriesTestUtils.givenGHRepositoryList(3)
        val ghSearchResponse = GHSearchResponse(3, items)
        whenever(searchRepositoriesUseCase.execute(any(), any())).thenReturn(Single.just(ghSearchResponse))

        repositoryListViewModel.searchNextPage(1)

        repositoryListViewModel.getPage().value shouldEqual 1
        repositoryListViewModel.getRepository().value shouldEqual items

        verify(searchRepositoriesUseCase, times(1)).execute(any(), any())
        verifyNoMoreInteractions(searchRepositoriesUseCase)
    }
}
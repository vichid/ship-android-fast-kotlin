package com.example.myapplication.ghrepositories

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.ext.orZero
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@SuppressLint("VisibleForTests")
class RepositoryListViewModel
@Inject
constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel() {

    private val repository: MutableLiveData<List<GHRepository>> = MutableLiveData()
    private val isLoading = MutableLiveData<Boolean>()
    private val isFailing = MutableLiveData<Boolean>()
    private val query: MutableLiveData<String> = MutableLiveData()
    private val page: MutableLiveData<Int> = MutableLiveData()
    private val sorting: MutableLiveData<String> = MutableLiveData()
    private val order: MutableLiveData<String> = MutableLiveData()
    private val snackbarMessage = SingleLiveEvent<Int>()

    init {
        query.value = "android"
        page.value = 1
        sorting.value = "stars"
        order.value = "desc"
    }

    fun start() {
        if (repository.value?.isEmpty() != false) {
            searchRepositories()
        }
    }

    fun getRepository(): LiveData<List<GHRepository>> = repository
    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getIsFailing(): LiveData<Boolean> = isFailing
    fun getQuery(): LiveData<String> = query
    fun getPage(): LiveData<Int> = page
    fun getSorting(): LiveData<String> = sorting
    fun getOrder(): LiveData<String> = order
    fun getSnackbarMessage(): LiveData<Int> = snackbarMessage

    private fun searchRepositories(fresh: Boolean = false) {
        val paramsSnapshot = SearchRepositoriesUseCase.Params(
            query.value.orEmpty(),
            page.value.orZero(),
            sorting.value.orEmpty(),
            order.value.orEmpty()
        )
        searchRepositoriesUseCase.execute(paramsSnapshot, fresh)
            .doOnSubscribe {
                isFailing.value = false
                isLoading.value = true
            }
            .doAfterTerminate { isLoading.value = false }
            .subscribe(
                { repository.value = repository.value.orEmpty().plus(it.items) },
                {
                    isFailing.value = true
                    handleError(it)
                }
            )
            .addTo(disposables)
    }

    fun searchNextPage(nextPage: Int) {
        page.value = nextPage
        searchRepositories()
    }

    fun refreshRepositories() {
        page.value = 1
        repository.value = emptyList()
        searchRepositories(fresh = true)
    }

    private fun handleError(t: Throwable) = when (t) {
        is HttpException -> {
        }
        is IOException -> {
        }
        else -> {
            snackbarMessage.setValue(R.string.error_dummy)
        }
    }
}

package com.example.myapplication.ghrepositories

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

class RepositoryListViewModel
@Inject
constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel() {

    val repository: MutableLiveData<List<GHRepository>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    val isFailing = MutableLiveData<Boolean>()
    val query: MutableLiveData<String> = MutableLiveData()
    val page: MutableLiveData<Int> = MutableLiveData()
    val sorting: MutableLiveData<String> = MutableLiveData()
    val order: MutableLiveData<String> = MutableLiveData()
    val snackbarMessage = SingleLiveEvent<Int>()

    init {
        query.value = "android"
        page.value = 1
        sorting.value = "stars"
        order.value = "desc"
        searchRepositories()
    }

    fun searchRepositories(fresh: Boolean = false) {
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
                { repository.value = (repository.value ?: emptyList()).plus(it.items) },
                {
                    isFailing.value = true
                    handleError(it)
                }
            )
            .addTo(disposables)
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

package com.example.myapplication.ghrepositories

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.StringRes
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryListViewModel
@Inject
constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel(), RepositoryListContract.Presenter {

    val snackbarMessage = SingleLiveEvent<Int>()
    val repository: MutableLiveData<List<GHRepository>> = MutableLiveData()
    val status: MutableLiveData<Status> = MutableLiveData()
    val query: MutableLiveData<String> = MutableLiveData()
    val page: MutableLiveData<Int> = MutableLiveData()
    val sorting: MutableLiveData<String> = MutableLiveData()
    val order: MutableLiveData<String> = MutableLiveData()

    override fun searchRepositories() {
        val paramsSnapshot = SearchRepositoriesUseCase.Params(
            query.value ?: "android",
            page.value ?: 1,
            sorting.value ?: "stars",
            order.value ?: "desc"
        )
        disposables.add(
            searchRepositoriesUseCase.execute(paramsSnapshot)
                .doOnSubscribe {
                    status.value = Status.Loading
                }
                .subscribe(
                    {
                        status.value = Status.Success
                        repository.postValue(
                            (repository.value ?: emptyList()).plus(it.items)
                        )
                    },
                    {
                        status.value = Status.Error
                        handleError(it)
                    }
                )
        )
    }

    override fun handleError(throwable: Throwable) = when (throwable) {
        is HttpException -> {
        }
        is IOException -> {
        }
        else -> {
        }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }
}

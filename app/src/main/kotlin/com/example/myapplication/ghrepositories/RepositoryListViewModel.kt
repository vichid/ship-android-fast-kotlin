package com.example.myapplication.ghrepositories

import android.arch.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase
import com.example.myapplication.util.Status
import com.example.myapplication.util.ext.orZero
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryListViewModel
@Inject
constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : BaseViewModel(), RepositoryListContract.ViewModel {

    val repository: MutableLiveData<List<GHRepository>> = MutableLiveData()
    val status: MutableLiveData<Status> = MutableLiveData()
    val query: MutableLiveData<String> = MutableLiveData()
    val page: MutableLiveData<Int> = MutableLiveData()
    val sorting: MutableLiveData<String> = MutableLiveData()
    val order: MutableLiveData<String> = MutableLiveData()

    override fun searchRepositories(fresh: Boolean) {
        val paramsSnapshot = SearchRepositoriesUseCase.Params(
            query.value.orEmpty(),
            page.value.orZero(),
            sorting.value.orEmpty(),
            order.value.orEmpty()
        )
        disposables.add(
            searchRepositoriesUseCase.execute(paramsSnapshot, fresh)
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
}

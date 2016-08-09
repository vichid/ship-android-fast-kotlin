package com.example.app.domain.usingcases

import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.domain.model.Github
import com.example.app.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
                                        private val githubRepository: GithubRepository) : UseCase<Github>(threadExecutor, postExecutionThread) {
    private var username: String = ""

    fun setId(username: String) {
        this.username = username
    }

    override fun buildUseCaseObservable(): Observable<Github> {
        return this.githubRepository.github(username)
    }
}
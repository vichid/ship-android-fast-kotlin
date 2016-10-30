package com.example.app.domain.interactor.github

import com.example.app.domain.exception.InvalidUsernameException
import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.domain.interactor.base.UseCase
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.domain.repository.GithubUserRepository
import rx.Observable
import javax.inject.Inject

class RetrieveGithubUserUseCase
@Inject
constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val githubUserRepository: GithubUserRepository
) : UseCase<GithubUserDomain>(threadExecutor, postExecutionThread) {

    private var username: String = ""

    /**
     * Initializes the interactor with the username.
     * @param username - username for the user
     */
    fun init(username: String): RetrieveGithubUserUseCase {
        this.username = username
        return this
    }

    /**
     * Builds the [UseCase] observable
     * @return an [] Observable that will emit the logged in [UserProfile]
     */
    override fun buildUseCaseObservable(): Observable<GithubUserDomain> {
        return Observable.concat(
                validate(),
                this.githubUserRepository[this.username]
        )
    }

    private fun validate(): Observable<Nothing> {
        return Observable.create { subscriber ->
            if (this@RetrieveGithubUserUseCase.username.isEmpty()) {
                subscriber.onError(InvalidUsernameException())
            } else {
                subscriber.onCompleted()
            }
        }
    }
}
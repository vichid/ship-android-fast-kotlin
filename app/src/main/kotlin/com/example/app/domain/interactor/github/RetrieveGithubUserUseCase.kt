package com.example.app.domain.interactor.github

import android.support.annotation.NonNull
import com.example.app.domain.exception.InvalidUsernameException
import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.domain.interactor.base.UseCase
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.domain.repository.GithubRepository
import rx.Observable
import rx.Subscriber
import javax.inject.Inject

class RetrieveGithubUserUseCase
@Inject
constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val githubRepository: GithubRepository
) : UseCase<GithubUserDomain>(threadExecutor, postExecutionThread) {

    private var username: String = ""

    /**
     * Initializes the interactor with the username.

     * @param username - username for the user
     */
    fun init(@NonNull username: String): RetrieveGithubUserUseCase {
        this.username = username
        return this
    }

    /**
     * Builds the [UseCase] observable
     * @return an [] Observable that will emit the logged in [UserProfile]
     */
    override fun buildUseCaseObservable(): Observable<GithubUserDomain> {
        return Observable.concat(validate(), this.githubRepository.retrieveGithubUser(this.username))
    }

    private fun validate(): Observable<Nothing> {
        return Observable.create(object : Observable.OnSubscribe<Nothing> {
            override fun call(subscriber: Subscriber<in Nothing>) {
                if (this@RetrieveGithubUserUseCase.username.isEmpty()) {
                    subscriber.onError(InvalidUsernameException())
                } else {
                    subscriber.onCompleted()
                }
            }
        })
    }
}
package com.example.app.domain.interactor.github

import com.example.app.domain.exception.InvalidUsernameException
import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.domain.interactor.base.UseCase
import com.example.app.domain.model.github.FollowerDomain
import com.example.app.domain.repository.GithubRepository
import rx.Observable
import rx.Subscriber
import javax.inject.Inject

class RetrieveFollowersUseCase
@Inject
constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val githubRepository: GithubRepository
) : UseCase<List<FollowerDomain>>(threadExecutor, postExecutionThread) {

    private var username: String = ""

    /**
     * Initializes the interactor with the username.

     * @param username - username for the user
     */
    fun init(username: String): RetrieveFollowersUseCase {
        this.username = username
        return this
    }

    /**
     * Builds the [UseCase] observable
     * @return an [] Observable that will emit the logged in [UserProfile]
     */
    override fun buildUseCaseObservable(): Observable<List<FollowerDomain>> {
        return Observable.concat(validate(), this.githubRepository.retrieveFollowers(this.username))
    }

    private fun validate(): Observable<Nothing> {
        return Observable.create(object : Observable.OnSubscribe<Nothing> {
            override fun call(subscriber: Subscriber<in Nothing>) {
                if (this@RetrieveFollowersUseCase.username.isEmpty()) {
                    subscriber.onError(InvalidUsernameException())
                } else {
                    subscriber.onCompleted()
                }
            }
        })
    }
}
package com.example.app.domain.interactor.github

import com.example.app.domain.exception.InvalidUsernameException
import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.domain.interactor.base.UseCase
import com.example.app.domain.model.github.FollowerDomain
import com.example.app.domain.repository.FollowerRepository
import rx.Observable
import javax.inject.Inject

class RetrieveUserFollowersByIdUseCase
@Inject
constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val followerRepository: FollowerRepository
) : UseCase<Collection<FollowerDomain>>(threadExecutor, postExecutionThread) {

    private var username: String = ""

    /**
     * Initializes the interactor with the username.
     * @param username - username for the user
     */
    fun init(username: String): RetrieveUserFollowersByIdUseCase {
        this.username = username
        return this
    }

    /**
     * Builds the [UseCase] observable
     * @return an [] Observable that will emit the logged in [UserProfile]
     */
    override fun buildUseCaseObservable(): Observable<Collection<FollowerDomain>> {
        return Observable.concat(
                validate(),
                this.followerRepository.retrieveUserFollowersById(this.username)
        )
    }

    private fun validate(): Observable<Nothing> {
        return Observable.create { subscriber ->
            if (this@RetrieveUserFollowersByIdUseCase.username.isEmpty()) {
                subscriber.onError(InvalidUsernameException())
            } else {
                subscriber.onCompleted()
            }
        }
    }
}
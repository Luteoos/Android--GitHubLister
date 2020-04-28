package io.github.luteoos.githublister.interfaces

import io.github.luteoos.githublister.data.android.GithubUsersWrapper
import io.reactivex.rxjava3.core.Flowable

interface GithubRepositoryInterface {
    fun getUsersFlowable() : Flowable<GithubUsersWrapper>
    fun fetchDataFromRest()
}
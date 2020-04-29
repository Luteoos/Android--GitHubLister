package io.github.luteoos.githublister.interfaces

import io.github.luteoos.githublister.data.android.GithubUsersWrapper
import io.reactivex.rxjava3.core.Observable

interface GithubRepositoryInterface {
    fun getUsersFlowable() : Observable<GithubUsersWrapper>
    fun fetchDataFromRest()
    fun getUsersFromRealm()
}
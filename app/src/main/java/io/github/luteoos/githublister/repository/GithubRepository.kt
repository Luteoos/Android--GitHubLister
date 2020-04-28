package io.github.luteoos.githublister.repository

import io.github.luteoos.githublister.data.android.GithubUsersWrapper
import io.github.luteoos.githublister.data.android.realm.GithubRepoRealm
import io.github.luteoos.githublister.data.android.realm.GithubUserRealm
import io.github.luteoos.githublister.data.rest.GithubReposRestResponse
import io.github.luteoos.githublister.interfaces.GithubRepositoryInterface
import io.github.luteoos.githublister.network.`interface`.GithubRESTInterface
import io.github.luteoos.githublister.utils.saveOrUpdateToRealm
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber

class GithubRepository(private val githubService: GithubRESTInterface) : GithubRepositoryInterface {
    private val stream : PublishSubject<GithubUsersWrapper> = PublishSubject.create()

    override fun getUsersFlowable(): Flowable<GithubUsersWrapper> {
        return stream.toFlowable(BackpressureStrategy.LATEST)
    }

    override fun fetchDataFromRest(){
        githubService
            .getUsers()
            .map {response ->
                when(response.code()){
                    200 -> response.body()
                    else -> throw Exception("${response.code()} ${response.message()}")
                }
            }
            .toFlowable()
            .flatMap {
                Flowable.fromIterable(it)
            }
            .map { user ->
                GithubUserRealm
                    .mapFromRest(user)
                    .saveOrUpdateToRealm()?.let {
                        fetchUserRepo(it)
                    }
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .subscribe({

            },{
                Timber.e(it)
                stream.onNext(GithubUsersWrapper(false))
            })
    }

    private fun fetchUserRepo(user: GithubUserRealm){
        githubService.getRepos(user.login!!)
            .map {response ->
                when(response.code()){
                    200 -> response.body() ?: listOf()
                    else -> throw Exception("${response.code()} ${response.message()}")
                }
            }
            .map {
                mapReposList(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                user.repos?.addAll(it)
                user.saveOrUpdateToRealm()
            },{
                Timber.e(it)
            })
    }

    private fun mapReposList(old: List<GithubReposRestResponse>) : List<GithubRepoRealm>{
        val list = mutableListOf<GithubRepoRealm>()
        old.forEach { restObject ->
            list.add(GithubRepoRealm.mapFromRest(restObject))
        }
        return list.toList()
    }
}
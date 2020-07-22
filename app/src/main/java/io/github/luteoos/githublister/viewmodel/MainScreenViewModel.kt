package io.github.luteoos.githublister.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.luteoos.githublister.baseAbstract.BaseViewModel
import io.github.luteoos.githublister.data.android.GithubUsersWrapper
import io.github.luteoos.githublister.interfaces.GithubRepositoryInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class MainScreenViewModel(private val githubRepository: GithubRepositoryInterface) : BaseViewModel() {

    private val usersList = MutableLiveData<GithubUsersWrapper>()

    init {
        subscribers.add(githubRepository.getUsersFlowable()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                usersList.value = it
                Timber.i("Success getUsersFlowabble")
            },{
                Timber.e(it)
            })
        )
    }

    fun getUsersLiveData() : LiveData<GithubUsersWrapper> = usersList

    fun getData(isNetworkAvailable: Boolean){
        when(isNetworkAvailable){
            true -> githubRepository.fetchDataFromRest()
            false -> githubRepository.getUsersFromRealm()
        }
    }

    fun getFilteredData(query: String?){
        query?.let {
            githubRepository.getFilteredUsersFromRealm(query)
        } ?: githubRepository.getUsersFromRealm()
    }
}
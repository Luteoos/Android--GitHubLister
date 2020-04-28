package io.github.luteoos.githublister.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.luteoos.githublister.baseAbstract.BaseViewModel
import io.github.luteoos.githublister.data.android.view.GithubUserDataObject
import io.github.luteoos.githublister.interfaces.GithubRepositoryInterface
import io.github.luteoos.githublister.utils.Parameters
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.realm.RealmResults
import timber.log.Timber

class MainScreenViewModel(private val githubRepository: GithubRepositoryInterface) : BaseViewModel() {

    private val usersList = MutableLiveData<RealmResults<GithubUserDataObject>>()

    init {
        subscribers.add(githubRepository.getUsersFlowable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.isSuccess)
                    usersList.value = it.list
                else
                    send(Parameters.NETWORK_ERROR)
            },{
                Timber.e(it)
            })
        )
    }

    fun getUsersLiveData() : LiveData<RealmResults<GithubUserDataObject>> = usersList

    fun getData(){
        //todo here if no internet get cached, is internet -> fetch
        githubRepository.fetchDataFromRest()
    }
}
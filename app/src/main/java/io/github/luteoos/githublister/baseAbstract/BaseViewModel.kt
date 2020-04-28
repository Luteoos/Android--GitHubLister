package io.github.luteoos.githublister.baseAbstract

import io.github.luteoos.mvvmbaselib.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : BaseViewModel() {
    protected val subscribers = CompositeDisposable()

    override fun onCleared() {
        subscribers.dispose()
        super.onCleared()
    }
}
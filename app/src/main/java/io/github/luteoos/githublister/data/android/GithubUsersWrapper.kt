package io.github.luteoos.githublister.data.android

import io.github.luteoos.githublister.data.android.view.GithubUserDataObject
import io.realm.RealmResults

data class GithubUsersWrapper(val isSuccess: Boolean,
                              val list: RealmResults<GithubUserDataObject>?){
    constructor(list: RealmResults<GithubUserDataObject>) : this(true, list)
    constructor(isSuccess: Boolean) : this(isSuccess, null)
}
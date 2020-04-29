package io.github.luteoos.githublister.data.android

import io.github.luteoos.githublister.data.android.realm.GithubUserRealm
import io.realm.RealmResults

data class GithubUsersWrapper(val isSuccess: Boolean,
                              val list: RealmResults<GithubUserRealm>?){
    constructor(list: RealmResults<GithubUserRealm>) : this(true, list)
    constructor(isSuccess: Boolean) : this(isSuccess, null)

    override fun toString(): String {
        return "GithubUsersWrapper $isSuccess"
    }
}
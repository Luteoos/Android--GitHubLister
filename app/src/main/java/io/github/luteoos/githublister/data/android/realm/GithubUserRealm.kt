package io.github.luteoos.githublister.data.android.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class GithubUserRealm : RealmObject(), RealmEntityInterface {
    @PrimaryKey
    override var id : Int? = null
    var login : String? = null
    var avatar_url : String? = null
    var repos : RealmList<GithubRepoRealm>? = null
}
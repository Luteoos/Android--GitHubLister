package io.github.luteoos.githublister.data.android.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class GithubRepoRealm : RealmObject(), RealmEntityInterface {
    @PrimaryKey
    override var id  : Int? = null
    var name : String? = null
}
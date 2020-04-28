package io.github.luteoos.githublister.data.android.realm

import io.github.luteoos.githublister.data.rest.GithubUsersRestResponse
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubUserRealm : RealmObject(), RealmEntityInterface {
    @PrimaryKey
    override var id : Int? = null
    var login : String? = null
    var avatar_url : String? = null
    var repos : RealmList<GithubRepoRealm>? = null

    companion object{
        fun mapFromRest(restObject: GithubUsersRestResponse) : GithubUserRealm{
            return GithubUserRealm().apply {
                id = restObject.id
                login = restObject.login
                avatar_url = restObject.avatar_url
                repos = RealmList()
            }
        }
    }
}
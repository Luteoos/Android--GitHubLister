package io.github.luteoos.githublister.data.android.realm

import io.github.luteoos.githublister.data.rest.GithubReposRestResponse
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubRepoRealm : RealmObject(), RealmEntityInterface {
    @PrimaryKey
    override var id  : Int? = null
    var name : String? = null

    companion object{
        fun mapFromRest(restObject: GithubReposRestResponse) : GithubRepoRealm{
            return GithubRepoRealm().apply {
                id = restObject.id
                name = restObject.name
            }
        }
    }
}
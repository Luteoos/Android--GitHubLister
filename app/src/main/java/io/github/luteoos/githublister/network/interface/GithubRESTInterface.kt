package io.github.luteoos.githublister.network.`interface`

import io.github.luteoos.githublister.data.rest.GithubReposRestResponse
import io.github.luteoos.githublister.data.rest.GithubUsersRestResponse
import io.github.luteoos.githublister.utils.Parameters.DEFAULT_REPO_PAGE_SIZE
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubRESTInterface {
    @GET("users")
    fun getUsers() : Single<Response<List<GithubUsersRestResponse>>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login")login: String,
                 @Query("per_page")perPage: Int = DEFAULT_REPO_PAGE_SIZE) : Single<Response<List<GithubReposRestResponse>>>
}
package io.github.luteoos.githublister.data.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubUsersRestResponse(
    @Expose
    @SerializedName("id")
    val id : Int,
    @Expose
    @SerializedName("login")
    val login : String,
    @Expose
    @SerializedName("avatar_url")
    val avatar_url : String

)
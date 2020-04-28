package io.github.luteoos.githublister.data.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubReposRestResponse(
    @Expose
    @SerializedName("id")
    val id : Int,
    @Expose
    @SerializedName("name")
    val name : Int
)
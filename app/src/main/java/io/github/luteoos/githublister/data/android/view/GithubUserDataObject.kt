package io.github.luteoos.githublister.data.android.view

data class GithubUserDataObject(val id: Int,
                                val name: String,
                                val avatar_url: String,
                                val repos: MutableList<GithubRepoDataObject> = mutableListOf())
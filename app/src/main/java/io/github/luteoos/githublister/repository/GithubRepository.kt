package io.github.luteoos.githublister.repository

import io.github.luteoos.githublister.interfaces.GithubRepositoryInterface
import io.github.luteoos.githublister.network.RestService

class GithubRepository(private val currencyService: RestService) : GithubRepositoryInterface {
}
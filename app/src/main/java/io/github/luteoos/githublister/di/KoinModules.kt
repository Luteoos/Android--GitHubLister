package io.github.luteoos.githublister.di

import io.github.luteoos.githublister.interfaces.GithubRepositoryInterface
import io.github.luteoos.githublister.network.GsonProvider
import io.github.luteoos.githublister.network.OkHttpClientProvider
import io.github.luteoos.githublister.network.RestService
import io.github.luteoos.githublister.repository.GithubRepository
import io.github.luteoos.githublister.viewmodel.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singleModule = module {
    single { GsonProvider() }
    single { OkHttpClientProvider() }
//    single { RestService(get(), get()) }
    single { RestService(get(), get()).getGithubUsersService() }
}

val factoryModule = module {
//    factory<GithubRESTInterface> { RestService(get(), get()).getGithubUsersService() }
    factory<GithubRepositoryInterface> { GithubRepository(get()) }
}

val vmModule = module {
    viewModel { MainScreenViewModel(get()) }
}

val koinModules = listOf(factoryModule, vmModule, singleModule)
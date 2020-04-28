package io.github.luteoos.githublister.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.github.luteoos.githublister.BuildConfig
import io.github.luteoos.githublister.network.`interface`.GithubRESTInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestService(private val gson: GsonProvider, private val httpClient: OkHttpClientProvider) {

    fun getCurrencyRatesService(): GithubRESTInterface =
        httpClient.getDefaultOkHttpClient().let { http ->
            getRetrofitBuilder()
                .client(http)
                .build()
                .create(GithubRESTInterface::class.java)
        }

    fun <T> getService(serviceInterface: Class<T>) : T{
        httpClient.getDefaultOkHttpClient().let { http ->
            getRetrofitBuilder().client(http).build().let {retrofit ->
                return retrofit.create(serviceInterface)
            }
        }
    }

    private fun getRetrofitBuilder() =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson.getDefaultGson()))
            .baseUrl(BuildConfig.BASE_URL)
}
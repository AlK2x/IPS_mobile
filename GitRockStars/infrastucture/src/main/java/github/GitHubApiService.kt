package github

import github.response.GitRepositoryList
import github.response.GitUsersList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header


interface GitHubApiService {
    @GET("user")
    fun login(@Header("Authorization") credentials: String): Observable<GitUsersList>

    @GET("search/repositories")
    fun searchRepositories(@Header("Authorization") credentials: String): Observable<GitRepositoryList>

    companion object Factory {
        fun create(authHeader: String): GitHubApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()

            return retrofit.create(GitHubApiService::class.java)
        }
    }
}
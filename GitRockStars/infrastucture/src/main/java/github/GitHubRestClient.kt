package github

import android.util.Base64
import github.response.GitRepositoryList
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.IOException


class GitHubRestClient(private val login: String, private val password: String) {

    private val client = OkHttpClient()
// любой метод возвращает поток, и подписываеся на него
    fun checkLogin(): Single<Boolean> { // TODO: return single
        val api = GitHubApiService.create(getBasicAuthStr())
        return api.login(getBasicAuthStr())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .singleOrError()
            .map { true }
    }

    fun searchRepositories(): Observable<GitRepositoryList> {
        return GitHubApiService.create(getBasicAuthStr())
            .searchRepositories(getBasicAuthStr())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    private fun run(url: String): String {
        val request = Request.Builder()
            .header("Authorization", getBasicAuthStr())
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful)
            throw IOException("Unexpected code $response")

        return response.body().toString()

    }

    private fun getBasicAuthStr(): String {
        return "Basic " + Base64.encode("$login:$password".toByteArray(), Base64.NO_WRAP)
    }
}
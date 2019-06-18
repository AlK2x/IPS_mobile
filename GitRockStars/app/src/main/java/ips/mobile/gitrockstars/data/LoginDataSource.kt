package ips.mobile.gitrockstars.data

import github.GitHubRestClient
import io.reactivex.Single
import ips.mobile.gitrockstars.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Single<Result<LoggedInUser>> {
        try {
            val c = GitHubRestClient(username, password)
            if (!c.checkLogin()) {
                return Result.Error(IOException("Login failed"))
            }

            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}


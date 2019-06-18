package github.response

data class Repository(
    val name: String,
    val owner: User
)

data class GitRepositoryList (val total_count: Int, val incomplete_results: Boolean, val items: List<Repository>)

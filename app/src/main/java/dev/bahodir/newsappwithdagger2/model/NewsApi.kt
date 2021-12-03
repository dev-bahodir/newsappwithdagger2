package dev.bahodir.newsappwithdagger2.model

data class NewsApi(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
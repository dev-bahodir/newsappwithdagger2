package dev.bahodir.newsappwithdagger2.retrofit

import dev.bahodir.newsappwithdagger2.model.NewsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getNewsApi(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "950f6bec33b0477280aeb3c90f0d9d5f"
    ): Response<NewsApi>
}
// ?q=sports&sortBy=publishedAt&apiKey=ca2e522a39ac49c088fe6438c1b9a1fe
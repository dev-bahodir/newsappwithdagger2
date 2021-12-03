package dev.bahodir.newsappwithdagger2.ui.main

import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.model.NewsApi

sealed class NewsResource {
    object Loading : NewsResource()
    data class Error(var error: String) : NewsResource()
    data class Success(var newsApi: NewsApi?) : NewsResource()
}
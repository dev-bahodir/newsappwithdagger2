package dev.bahodir.newsappwithdagger2.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.model.Article
import dev.bahodir.newsappwithdagger2.network.NetworkHelper
import dev.bahodir.newsappwithdagger2.repository.NewsRepository
import dev.bahodir.newsappwithdagger2.ui.main.NewsResource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private var newsRepository: NewsRepository,
    private var networkHelper: NetworkHelper
) : ViewModel() {

    suspend fun addArticleVM(saveEntity: SaveEntity) {
        newsRepository.addDB(saveEntity)
    }

    suspend fun deleteArticleVM(saveEntity: SaveEntity) {
        newsRepository.deleteDB(saveEntity)
    }

    suspend fun deleteIdArticleVM(id: Int) {
        newsRepository.deleteIdDB(id)
    }

    suspend fun getArticleVM(): Flow<List<SaveEntity>> {
        return newsRepository.getDB()
    }

    fun getNewsApi(q: String): StateFlow<NewsResource> {
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                newsRepository
                    .getNewsApi(q = q)
                    .catch {
                        stateFlow.emit(NewsResource.Error(it.message ?: ""))
                    }
                    .collect {
                        if (it.isSuccessful) {
                            stateFlow.emit(NewsResource.Success(it.body()))
                        } else {
                            stateFlow.emit(NewsResource.Error("Failure"))
                        }
                    }
            } else {
                stateFlow.emit(NewsResource.Error("No internet connected"))
            }
        }
        return stateFlow
    }

}
package dev.bahodir.newsappwithdagger2.repository

import dev.bahodir.newsappwithdagger2.data.SaveDao
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.retrofit.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private var apiService: ApiService,
    private var saveDao: SaveDao
) {
    suspend fun getNewsApi(q: String) = flow { emit(apiService.getNewsApi(q = q)) }

    suspend fun addDB(saveEntity: SaveEntity) = saveDao.add(saveEntity)

    suspend fun deleteDB(saveEntity: SaveEntity) = saveDao.delete(saveEntity)

    suspend fun deleteIdDB(id: Int) = saveDao.deleteById(id)

    suspend fun getDB() = flow { emit(saveDao.getList()) }
}
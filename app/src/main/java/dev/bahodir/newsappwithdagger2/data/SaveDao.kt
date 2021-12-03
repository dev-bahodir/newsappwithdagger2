package dev.bahodir.newsappwithdagger2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface SaveDao {
    @Insert(onConflict = REPLACE)
    suspend fun add(saveEntity: SaveEntity)

    @Delete
    suspend fun delete(saveEntity: SaveEntity)

    @Query("DELETE FROM save_entity WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM save_entity")
    suspend fun getList(): List<SaveEntity>
}
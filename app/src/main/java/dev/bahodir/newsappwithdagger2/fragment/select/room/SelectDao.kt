package dev.bahodir.newsappwithdagger2.fragment.select.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import dev.bahodir.newsappwithdagger2.fragment.select.model.SelectUser

@Dao
interface SelectDao {

    @Insert(onConflict = REPLACE)
    fun add(selectUser: SelectUser)

    @Delete
    fun delete(selectUser: SelectUser)

    @Query("DELETE FROM select_user WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM select_user")
    fun getList(): List<SelectUser>
}
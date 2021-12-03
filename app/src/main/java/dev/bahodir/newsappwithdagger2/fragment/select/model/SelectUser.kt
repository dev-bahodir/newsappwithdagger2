package dev.bahodir.newsappwithdagger2.fragment.select.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "select_user")
data class SelectUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var tv: String = "",
    var bool: Boolean = false
)
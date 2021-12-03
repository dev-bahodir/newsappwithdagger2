package dev.bahodir.newsappwithdagger2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "save_entity")
data class SaveEntity(
    @PrimaryKey
    var id: String,
    var image: String,
    var title: String,
    var description: String
)
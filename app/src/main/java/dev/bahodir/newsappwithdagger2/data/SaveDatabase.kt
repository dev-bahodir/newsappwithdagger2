package dev.bahodir.newsappwithdagger2.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SaveEntity::class], version = 1)
abstract class SaveDatabase : RoomDatabase() {
    abstract fun saveDao(): SaveDao
}
package dev.bahodir.newsappwithdagger2.fragment.select.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.bahodir.newsappwithdagger2.fragment.select.model.SelectUser

@Database(entities = [SelectUser::class], version = 1)
abstract class SelectDB : RoomDatabase() {
    abstract fun selectDao() : SelectDao

    companion object {
        private var instance : SelectDB? = null

        @Synchronized
        fun getInstance(context: Context) : SelectDB {
            if (instance == null) {
                instance =  Room
                    .databaseBuilder(context, SelectDB::class.java, "select_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}
package dev.bahodir.newsappwithdagger2.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.bahodir.newsappwithdagger2.data.SaveDao
import dev.bahodir.newsappwithdagger2.data.SaveDatabase
import javax.inject.Singleton

@Module
class DatabaseModule(private var context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideSaveDatabase(context: Context): SaveDatabase {
        return Room.databaseBuilder(context, SaveDatabase::class.java, "save_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSaveDao(saveDatabase: SaveDatabase): SaveDao = saveDatabase.saveDao()
}
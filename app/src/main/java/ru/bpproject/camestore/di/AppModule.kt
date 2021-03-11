package ru.bpproject.camestore.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.bpproject.camestore.data.localdb.AppDatabase
import ru.bpproject.camestore.data.localdb.Dao
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDb(app: Application) : AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db : AppDatabase) : Dao {
        return db.dao()
    }
}
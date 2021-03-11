package ru.bpproject.camestore.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bpproject.camestore.data.localdb.entity.AccessoriesByProductEntity
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity
import ru.bpproject.camestore.data.localdb.entity.ProductEntity

const val DATABASE_VERSION = 6

@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        AccessoriesByProductEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao

}
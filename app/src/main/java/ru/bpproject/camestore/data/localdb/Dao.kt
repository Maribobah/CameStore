package ru.bpproject.camestore.data.localdb

import android.provider.BaseColumns
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity
import ru.bpproject.camestore.data.localdb.entity.CategoryWithProducts
import ru.bpproject.camestore.data.localdb.entity.ProductEntity

@Dao
interface Dao {

    @Query(
        """
        SELECT * FROM "categories"
        ORDER BY ${BaseColumns._ID}
    """
    )
    suspend fun getCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoryIfNotExist(category: CategoryEntity): Long

    @Query(
        """
        SELECT * FROM "categories"
        WHERE ${BaseColumns._ID} = :id
    """
    )
    suspend fun getCategoryWithProducts(id: Long): List<CategoryWithProducts>

    @Query("""
        SELECT * FROM "products"
        WHERE ${BaseColumns._ID} = :id
    """)
    suspend fun getProductById(id: Long): ProductEntity

    @Query("""
        SELECT Products.* FROM "accessories_by_product" T
        INNER JOIN "products" Products ON T.accessory = Products._id
        WHERE T.product = :productId
    """)
    suspend fun getProductAccessories(productId: Long) : List<ProductEntity>

}